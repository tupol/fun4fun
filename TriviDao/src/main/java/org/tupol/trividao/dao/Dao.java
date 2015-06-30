package org.tupol.trividao.dao;

import java.util.List;

import org.tupol.trividao.model.PersistentBean;

/**
 * This is the common Data Object Access interface, covering the basic
 * operations (CRUD) and a few basic helpers.
 * 
 * @author tupol
 * 
 * @param <T>
 */
public interface Dao<T extends PersistentBean> {

	/**
	 * Persist the given transient data object (no id defined).
	 * 
	 * @param data
	 *            transient object to be persisted
	 * @return persisted object (id is defined)
	 */
	public T store(T data);

	/**
	 * Update the given data object
	 * 
	 * @param data
	 *            persistent object containing modifications
	 * @return persisted object
	 */
	public T update(T data);

	/**
	 * Delete the given data object
	 * 
	 * @param data
	 *            the persisted object to be deleted
	 */
	public void delete(T data);

	/**
	 * Find a persisted object by id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id);

	/**
	 * List all objects of type T from the database.
	 * <p>
	 * <b>WARNING!</b> <i><u>This should not be used for large sets of
	 * data!</u></i>
	 * 
	 * @return the list of all objects of type T
	 */
	public List<T> findAll();

	/**
	 * Count all objects of type T from the database.
	 * 
	 * @return the number of objects of type T from the database.
	 */
	public Long countAll();
}
