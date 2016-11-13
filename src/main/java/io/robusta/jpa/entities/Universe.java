package io.robusta.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.robusta.jpa.HasId;

@Entity
public class Universe implements HasId{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	
	String name;

	public Universe() {
	
	}
	public Universe(String name) {
		this.name = name;
	}
	
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
}
