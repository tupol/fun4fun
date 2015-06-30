package org.tupol.sample_1.model;

import java.util.HashSet;
import java.util.Set;

import org.tupol.trividao.model.AbstractPersistentBean;
import org.tupol.trividao.model.PersistentBean;

/**
 * Dummy persistent customer, as it only has a name.
 * 
 * @author tupol
 *
 */
public class Customer extends AbstractPersistentBean implements PersistentBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private Set<Order> orders = new HashSet<>();

	/**
	 * Default constructor, useful for Hibernate
	 */
	public Customer() {
	}

	/**
	 * Meaningful constructors helping us building the bean are good in most of
	 * the cases.
	 * 
	 * @param name
	 */
	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String toString() {
		// Always a good idea to override toString in this context
		return String.format("%s; name='%s'", super.toString(), name);
	}

}
