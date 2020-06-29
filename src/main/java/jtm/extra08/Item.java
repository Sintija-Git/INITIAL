package jtm.extra08;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//TODO Annotate class as an @Entity, to say that its instances can be stored in the database.
@Entity
public class Item {
	// TODO Annotate id field as an @Id, to mark it as a primary key field.
	@Id
	private Integer id;
	// TODO annotate invoice property with
	// @ManyToOne @JoinColumn to define that its value will be used as
	// many-to-one relation between item and invoice
	
	@ManyToOne
	@JoinColumn
	private Invoice invoice;
	private String name;
	private Integer count;
	private Float price;

	
	@Override
	// Do not change this method, it is implemented already
	public String toString() {
		return "'Item:" + id + ", name:" + name + ", price:" + price + ", count:" + count + ", total:" + price * count
				+ "'";
	}

	
	// TODO generate getters and setters for item properties
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
