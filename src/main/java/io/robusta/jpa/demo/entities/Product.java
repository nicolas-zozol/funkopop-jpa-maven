package io.robusta.jpa.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

@Entity
public class Product{

	@Id
	@GeneratedValue
	int id;
	
	
	//@Length(min=8, max=12)
	@Column(nullable=false)
	String name;
	
	
	//@Min(0)
	float price;
	
	@ManyToOne(fetch=FetchType.LAZY)
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
