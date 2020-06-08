package jtm.activity09;

/*- TODO #1
 * Implement Comparable interface with Order class
 * Hint! Use generic type of comparable items in form: Comparable<Order>
 * 
 * TODO #2 Override/implement necessary methods for Order class:
 * - public Order(String orderer, String itemName, Integer count) — constructor of the Order
 * - public int compareTo(Order order) — comparison implementation according to logic described below
 * - public boolean equals(Object object) — check equality of orders
 * - public int hashCode() — to be able to handle it in some hash... collection 
 * - public String toString() — string in following form: "ItemName: OrdererName: Count"
 * 
 * Hints:
 * 1. When comparing orders, compare their values in following order:
 *    - Item name
 *    - Customer name
 *    - Count of items
 * If item or customer is closer to start of alphabet, it is considered "smaller"
 * 
 * 2. When implementing .equals() method, rely on compareTo() method, as for sane design
 * .equals() == true, if compareTo() == 0 (and vice versa).
 * 
 * 3. Also Ensure that .hashCode() is the same, if .equals() == true for two orders.
 * 
 */

public class Order implements Comparable<Order> {
	String customer; // Name of the customer
	String name; // Name of the requested item
	int count; // Count of the requested items
	

	// public Order(String orderer, String itemName, Integer count) — constructor of
	// the Order
	public Order(String orderer, String itemName, Integer count) {
		customer = orderer;
		name = itemName;
		this.count = count;
	}

	
	// public int compareTo(Order order) — comparison implementation according to
	// logic described below
	@Override
	public int compareTo(Order object) {

		int result = this.name.compareTo(object.name);
		if (result > 0) {
			return 1;
		} else if (result < 0) {
			return -1;
		} else if (result == 0) {
			result = this.customer.compareTo(object.customer);
			if (result > 0) {
				return 1;
			} else if (result < 0) {
				return -1;
			} else if (result == 0) {
				result = Integer.compare(this.count, object.count);
			}
		}
		return result;
	}

	
	@Override
	public boolean equals(Object object) { // — check equality of orders

		if (object instanceof Order) {

			Order order = (Order) object;
			return this.name.equals(order.name) && this.customer.equals(order.customer) && this.count == order.count; // if (this.compTo(order)==0{ return true
		} else {
			return false;
		}
	}
	

	@Override
	public int hashCode() { // — to be able to handle it in some hash...collection

		final int prime = 31; //to string().hashCode();
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() { // — string in following form: "ItemName: OrdererName: Count"

		return name + ": " + customer + ": " + count;
	}

	
	// getters
	public String getCustomer() {
		return customer;
	}

	public String getItemName() {
		return name;
	}

	public int getCount() {
		return count;
	}

} // end class
