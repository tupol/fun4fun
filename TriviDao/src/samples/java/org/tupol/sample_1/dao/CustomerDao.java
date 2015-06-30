package org.tupol.sample_1.dao;

import java.util.List;

import org.tupol.sample_1.model.Customer;
import org.tupol.trividao.dao.Dao;

/**
 * DAO interface for {@link Customer} objects
 * 
 * @author tupol
 *
 */
public interface CustomerDao extends Dao<Customer> {

	public List<Customer> findByName(String name);

	public List<Object[]> findBestCustomers(int howMany);

}