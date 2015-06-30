package org.tupol.sample_1.dao;

import java.util.List;

import org.hibernate.Query;
import org.tupol.sample_1.model.Customer;
import org.tupol.trividao.hibernate.HibernateGenericDao;

/**
 * Hibernate implementation for {@link CustomerDao}
 * 
 * @author tupol
 *
 */
public class CustomerDaoImpl extends HibernateGenericDao<Customer> implements CustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);
	}

	public List<Customer> findByName(String name) {

		String queryString = String.format("from %s where name = :name",
				getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setParameter("name", name);
		return query.list();
	}

	@Override
	public List<Object[]> findBestCustomers(int howMany) {

		String queryString = String
				.format("select customers, sum(items.price * items.quantity) as total from Customer as customers join customers.orders as orders join orders.items as items group by customers order by sum(items.price * items.quantity) desc",
						getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setMaxResults(howMany);
		return query.list();
	}

}
