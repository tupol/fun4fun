package org.tupol.sample_1.dao;

import java.util.List;

import org.hibernate.Query;
import org.tupol.sample_1.model.Customer;
import org.tupol.sample_1.model.Order;
import org.tupol.trividao.hibernate.HibernateGenericDao;

/**
 * Hibernate implementation for {@link OrderDao}
 * 
 * @author tupol
 *
 */
public class OrderDaoImpl extends HibernateGenericDao<Order> implements
		OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}

	public List<Order> findByCustomer(Customer customer) {

		String queryString = String.format(
				"from %s where customer_id = :customer_id", getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setParameter("customer_id", customer.getId());
		return query.list();

	}

	@Override
	public List<Order> findTotalLargerThan(double total) {

		String queryString = "select orders from Order as orders left join orders.items as items group by orders having sum(items.price * items.quantity) >= :total ";
		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setParameter("total", total);
		return query.list();
	}

}
