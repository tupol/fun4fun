package org.tupol.trividao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tupol.trividao.dao.Dao;
import org.tupol.trividao.model.PersistentBean;

/**
 * Generic abstract <a href="http://hibernate.org/orm//">Hibernate</a> DAO,
 * implementing the common DAO methods (CRUD)
 * <p>
 * Transaction management will be done with Spring AOP in a declarative style.
 * 
 * @see Dao
 * @see PersistentBean
 * @param <T>
 *            the persisted class type
 * @author tupol
 */
public abstract class HibernateGenericDao<T extends PersistentBean> implements
		Dao<T> {

	private final Class<T> persistentClass;

	protected SessionFactory sessionFactory;

	/**
	 * This constructor needs to be used by implementing classes in order to
	 * define the associated class of type T.
	 * 
	 * @param persistentClass
	 */
	protected HibernateGenericDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get the entity name for the managed class type.
	 * <p>
	 * <b>Warning!</b> This only works for auto imports. If auto imports are off
	 * or there is a more complex scenario then the full class name should be
	 * returned. However, for starters, the simple name makes the queries more
	 * readable.
	 * 
	 * @return entity name for the managed class type.
	 */
	protected String getEntityName() {
		return persistentClass.getSimpleName();
	}

	public void delete(T data) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(data);
		session.flush();
	}

	public T store(T data) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(getEntityName(), data);
		session.flush();
		return data;

	}

	public T update(T data) {

		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().update(data);
		session.flush();
		return data;

	}

	public List<T> findAll() {


		String queryString = String.format("from %s",
				getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		return query.list();
		
	}

	public T findById(String id) {

		String queryString = String.format("from %s where id = :id",
				getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setParameter("id", id);
		return (T) query.uniqueResult();
	}

	public Long countAll() {

		String queryString = String.format("select count (*) from %s",
				getEntityName());

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		return (Long) query.uniqueResult();

	}

}
