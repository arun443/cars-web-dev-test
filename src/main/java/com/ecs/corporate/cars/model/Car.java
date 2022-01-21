package com.ecs.corporate.cars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

	private long id;
	private String make;
	private String model;
	private String colour;
	private String year;

	
	public Car() {
		
	}
	
	public Car(String make, String model, String colour ,String year ) {
		this.make = make;
		this. model =  model;
		this.colour = colour;
		this.year = year;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "make", nullable = false)
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	@Column(name = "model", nullable = false)
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name = "colour", nullable = false)
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	
	@Column(name = "year", nullable = false)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", Make=" + make + ", Model=" + model + ", Colour=" + colour+ ", Year=" + year+ "]";
	}
	
}
