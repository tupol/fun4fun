package org.tupol.sample_1;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tupol.sample_1.dao.CustomerDao;
import org.tupol.sample_1.dao.OrderDao;
import org.tupol.sample_1.model.Customer;
import org.tupol.sample_1.model.Order;

/**
 * This class is a small sample of how the TriviDao (Trivial DAO) package can be
 * used.
 * <p>
 * Customers area created modified, deleted, they get orders and the database
 * gets queried for a few simple results.
 * 
 * @author tupol
 *
 */
public class SampleApp {

	private CustomerDao customerDao;
	private OrderDao orderDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void runSample() {

		System.out.println("\nAdding customers...");
		Customer cust_alex = customerDao.store(new Customer("Alex"));
		Customer cust_john = customerDao.store(new Customer("John"));
		Customer cust_mark = customerDao.store(new Customer("Mark"));
		Customer cust_tony = customerDao.store(new Customer("Tony"));

		System.out.println("\nCustomers added:");
		printCustomersOrdersAndItems(customerDao.findAll());

		System.out
				.println("\nTotal customers added: " + customerDao.countAll());

		System.out.println("\nModified customer Alex name to Bart.");
		cust_alex.setName("Bart");
		customerDao.update(cust_alex);

		System.out.println("\nCustomers found by name \"Alex\":");
		if (customerDao.findByName("Alex").size() > 0) {
			for (Customer customer : customerDao.findByName("Alex")) {
				System.out.println("  " + customer);
			}
		} else {
			System.out.println("  NONE");
		}

		System.out.println("\nAdding an order for customer Bart...");
		Order cust1ord1 = new Order(cust_alex);
		cust1ord1.getItems().add(new Order.OrderItem("Phone", 2, 200.0));
		cust1ord1.getItems().add(new Order.OrderItem("Headset", 1, 10.0));
		orderDao.store(cust1ord1);

		System.out.println("\nAdding an order for customer John...");
		Order cust2ord1 = new Order(cust_john);
		cust2ord1.getItems().add(new Order.OrderItem("Phone", 3, 200.0));
		cust2ord1.getItems().add(new Order.OrderItem("Headset", 1, 10.0));
		orderDao.store(cust2ord1);

		System.out.println("\nAdding an order for customer Mark...");
		Order cust3ord1 = new Order(cust_mark);
		cust3ord1.getItems().add(new Order.OrderItem("Phone", 1, 200.0));
		cust3ord1.getItems().add(new Order.OrderItem("Headset", 2, 10.0));
		orderDao.store(cust2ord1);

		System.out.println("\nCustomers , orders and order items:");
		printCustomersOrdersAndItems(customerDao.findAll());

		System.out.println("\nOrders having the total larger than 10");
		printOrdersAndItems(orderDao.findTotalLargerThan(10));

		System.out.println("\nOrders having the total larger than 500");
		printOrdersAndItems(orderDao.findTotalLargerThan(500));

		System.out.println("\nFind best customer");
		printCustomersAndTotalSpent(customerDao.findBestCustomers(1));

		System.out.println("\nFind best 2 customers");
		printCustomersAndTotalSpent(customerDao.findBestCustomers(2));

		System.out.println("\nDelete customer John.");
		customerDao.delete(cust_john);

		System.out.println("\nCustomers found by name \"John\":");
		printCustomers(customerDao.findByName("John"));

		System.out.println("\nThe best customer now is:");
		printCustomersAndTotalSpent(customerDao.findBestCustomers(1));

		System.out
				.println("\nCustomers, orders and order items (our big spender John is gone):");
		printCustomersOrdersAndItems(customerDao.findAll());

		System.out.println("\nDeleting all customers...");
		for (Customer customer : customerDao.findAll()) {
			customerDao.delete(customer);
		}

		System.out.println("\nRemaining customers:");
		printCustomersOrdersAndItems(customerDao.findAll());

	}

	private void printCustomersOrdersAndItems(List<Customer> customers) {
		assert customers != null;
		if (customerDao.findAll().size() == 0)
			System.out.printf("  NONE %n");
		else {
			for (Customer customer : customers) {
				System.out.printf("  %s %n", customer);
				if (customer.getOrders() != null) {
					for (Order order : customer.getOrders()) {
						System.out.printf("    %s %n", order);
						for (Order.OrderItem item : order.getItems()) {
							System.out.printf("      %s %n", item);
						}
					}
				}
			}
		}
	}

	private void printCustomers(List<Customer> customers) {
		assert customers != null;
		if (customerDao.findAll().size() == 0)
			System.out.printf("  NONE %n");
		else {
			for (Customer customer : customers) {
				System.out.printf("  %s %n", customer);
			}
		}
	}

	private void printCustomersAndTotalSpent(List<Object[]> results) {
		assert results != null;
		for (Object[] result : results) {
			System.out.printf("    %s spent an impressive %s doubloons.%n",
					result[0], result[1]);
		}
	}

	private void printOrdersAndItems(List<Order> orders) {
		assert orders != null;
		for (Order order : orders) {
			System.out.printf("    %s | %s %n", order, order.getCustomer());
			for (Order.OrderItem item : order.getItems()) {
				System.out.printf("      %s %n", item);
			}
		}

	}

	public static void main(String... args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"org/tupol/sample_1/applicationContext.xml");
		((AbstractApplicationContext) context).registerShutdownHook();

		SampleApp sampleApp = (SampleApp) context.getBean("sampleApp");
		sampleApp.runSample();

	}

}
