package jtm.activity09;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;

/*- TODO #2
 * Implement Iterator interface with Orders class
 * Hint! Use generic type argument of iterateable items in form: Iterator<Order>
 * 
 * TODO #3 Override/implement public methods for Orders class:
 * - Orders()               	 — create new empty Orders
 * - add(Order item)            — add passed order to the Orders
 * - List<Order> getItemsList() — List of all customer orders
 * - Set<Order> getItemsSet()   — calculated Set of Orders from list (look at description below)
 * - sort()                     — sort list of orders according to the sorting rules
 * - boolean hasNext()          — check is there next Order in Orders
 * - Order next()               — get next Order from Orders, throw NoSuchElementException if can't
 * - remove()                   — remove current Order (order got by previous next()) from list, throw IllegalStateException if can't
 * - String toString()          — show list of Orders as a String
 * 
 * Hints:
 * 1. To convert Orders to String, reuse .toString() method of List.toString()
 * 2. Use built in List.sort() method to sort list of orders
 * 
 * TODO #4
 * When implementing getItemsSet() method, join all requests for the same item from different customers
 * in following way: if there are two requests:
 *  - ItemN: Customer1: 3
 *  - ItemN: Customer2: 1
 *  Set of orders should be:
 *  - ItemN: Customer1,Customer2: 4
 */

public class Orders implements Iterator<Order> {

	String customer; // Name of the customer
	String name; // Name of the requested item
	int count; // Count of the requested items

	/*-
	 * TODO #1
	 * Create data structure to hold:
	 *   1. some kind of collection of Orders (e.g. some List)
	 *   2. index to the current order for iterations through the Orders in Orders
	 *   Hints:
	 *   1. you can use your own implementation or rely on .iterator() of the List
	 *   2. when constructing list of orders, set number of current order to -1
	 *      (which is usual approach when working with iterateable collections).
	 */

	List<Order> ordersList = new LinkedList<Order>();
	ListIterator<Order> itr = ordersList.listIterator();


	public Orders() {
		ordersList = new LinkedList<Order>();
	}

	public void add(Order item) { // — add passed order to the Orders
		ordersList.add(item);

	}

	public List<Order> getItemsList() { // — List of all customer orders
		return ordersList;

	}

	public void sort() { // — Use built in List.sort() method to sort list of orders
		Collections.sort(ordersList);
	}

	/*
	 * When implementing getItemsSet() method, join all requests for the same item
	 * from different customers in following way: if there are two requests: -
	 * ItemN: Customer1: 3 - ItemN: Customer2: 1 Set of orders should be: - ItemN:
	 * Customer1,Customer2: 4
	 */
	public Set<Order> getItemsSet() { // — calculated Set of Orders from list (look at description below)
		
		Set<Order> set = new HashSet<>();

		for (int i = 0; i < ordersList.size(); i++) {
			Order current = ordersList.get(i);
			if (!set.contains(current)) {
				set.add(current);
				System.out.println(set);
			}

		}
		return set;
	}

	@Override
	public boolean hasNext() { // — check is there next Order in Orders
		if (itr.hasNext()) {
			return true;
		}
		return false;
	}
	

	@Override
	public Order next() { // — get next Order from Orders, throw NoSuchElementException if can't

		if (itr.hasNext()) {
			return itr.next();
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() { // — remove current Order (order got by previous next()) from list, throw
							// IllegalStateException if can't

		if (itr.hasNext()) {
			itr.remove();
		} else {
			throw new IllegalStateException();

		}
	}

	@Override
	public String toString() { // — show list of Orders as a String
		return ordersList.toString();

	}

} // end class
