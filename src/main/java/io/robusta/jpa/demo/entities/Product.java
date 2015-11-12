package io.robusta.jpa.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue
	int id;
	String name;
	float price;
	
	
	
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
	
	
	
}
