package jtm.extra08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class InvoiceManager {
	EntityManagerFactory emf; // factory class to create new EntityManager
	EntityManager em; // entity manager to manage persistence
	EntityTransaction entityTransaction; // optional transaction

	public InvoiceManager() {
		// Initialize EntityManager using local H2 database
		emf = Persistence.createEntityManagerFactory("h2-local");
		em = emf.createEntityManager();
		// Initialize EnitityTransaction to manage transactions directly
		entityTransaction = em.getTransaction();
	}

	public Invoice createInvoice(Integer id) {
		// TODO Create new Invoice, set its id
		Invoice invoice = new Invoice();
		invoice.setId(id);
		
		// Save invoice object into database
		persist(invoice);
		return invoice;
	}

	public Item createInvoiceItem(Invoice invoice, Integer id, String name, Float price, Integer quantity) {
		// TODO create new Item, set its properties and save it in database
		// TODO return reference to the created item
		Item invItem = new Item();
		invItem.setInvoice(invoice);
		invItem.setId(id);
		invItem.setName(name);
		invItem.setPrice(price);
		invItem.setCount(quantity);
		
		persist(invItem);
		return invItem;
	}

	public Item createItem(Integer id, String name, Float price, Integer quantity) {
		// TODO create new Item, set its properties and save it in database
		// and return reference to it
		Item item = new Item();
		item.setId(id);
		item.setName(name);
		item.setPrice(price);
		item.setCount(quantity);
		persist(item);
		return item;
	}

	public void addItemToInvoice(Invoice invoice, Item item) {
		// TODO add passed item to the passed invoice
		invoice.addItem(item);
		
	}

	public void persist(Object o) {
		// Saves any passed object into database using EntityManager
		try {
			entityTransaction.begin();
			em.persist(o);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
	}

	public Invoice searchInvoice(Integer id) {
		// Searches for invoice in database, using its unique id
		return em.find(Invoice.class, id);
	}

	public Item searchItem(Integer id) {
		// TODO search item in database and return reference to it
		return em.find(Item.class, id);
	}

	public void clearData() {
		// Use this method to delete any previous data from database tables
		entityTransaction.begin();
		em.createNativeQuery("delete from item; delete from invoice;").executeUpdate();
		entityTransaction.commit();
	}

	public static void main(String[] args) {
		// By default, run Junit test if started as Java application
		// Feel free to change this method for your own needs
		InvoiceManagerTest test = new InvoiceManagerTest();
		test.testInvoiceToItem();
		test.testItemToInvoice();
	}
}
