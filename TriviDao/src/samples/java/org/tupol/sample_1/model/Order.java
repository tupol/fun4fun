package org.tupol.sample_1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tupol.trividao.model.AbstractPersistentBean;
import org.tupol.trividao.model.PersistentBean;

/**
 * Dummy persistent order, very basic data inside.
 * 
 * @author tupol
 *
 */
public class Order extends AbstractPersistentBean implements PersistentBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;

	private List<OrderItem> items = new ArrayList<>();

	/**
	 * the timestamp of the order
	 */
	private Date timestamp = new Date();

	public Order() {
	}

	public Order(Customer customer) {
		this.customer = customer;
		customer.getOrders().add(this);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Double getTotal() {
		double total = 0;
		for (OrderItem item : items)
			total += item.price * item.quantity;
		return total;
	}


	public String toString() {
		// Always a good idea to override toString in this context
		return String.format("%s; total='%s'; timestamp='%s'",
				super.toString(), getTotal(), timestamp);
	}

	/**
	 * For our purposes we can keep the OrderItem inside the order. Let's assume
	 * that the order item can not exist outside the order context.
	 * 
	 * @author oliver
	 *
	 */
	public static class OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private String name;
		private int quantity;
		private double price;

		public OrderItem() {
		}

		public OrderItem(String name, int quantity, double price) {
			setName(name);
			setQuantity(quantity);
			setPrice(price);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String toString() {
			// Always a good idea to override toString in this context
			return String.format(
					"%s; %s; quantity='%d'; price='%3.2f'; total='%3.2f'", this
							.getClass().getSimpleName(), name, quantity, price,
					(quantity * price));
		}

	}
}
