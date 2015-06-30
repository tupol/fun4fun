package org.tupol.sample_1.dao;

import java.util.List;

import org.tupol.sample_1.model.Customer;
import org.tupol.sample_1.model.Order;
import org.tupol.trividao.dao.Dao;

/**
 * DAO interface for {@link Order} objects
 * 
 * @author tupol
 *
 */
public interface OrderDao extends Dao<Order> {

	/**
	 * Find all the orders corresponding to a given customer.
	 * 
	 * @param customer
	 * @return
	 */
	public List<Order> findByCustomer(Customer customer);

	/**
	 * Find all orders having the total larger or equal with the specified
	 * value.
	 * 
	 * @param total
	 * @return
	 */
	public List<Order> findTotalLargerThan(double total);

}