package io.robusta.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Universe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String name;
}
