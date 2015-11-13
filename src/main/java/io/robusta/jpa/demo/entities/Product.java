package io.robusta.jpa.demo.entities;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Product{

	@Id
	@GeneratedValue
	int id;
	
	@Basic(optional=false)
	String name;
	
	float price;
	
	@ManyToOne
	Category category;
	
	
	public Product() {
	}

	public Product(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	

	public int getId() {
		return id;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	
	
	
}
