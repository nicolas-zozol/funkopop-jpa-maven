package io.robusta.jpa.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {

	//Do we need an int id ?
	@Id
	@GeneratedValue
	int id;
	String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	Category parent;
	
	
	public Category() {
		
	}

	public Category(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	
}
