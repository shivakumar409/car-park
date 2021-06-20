package com.carpark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "PARKING_SLOT" )
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
public class ParkSlot {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "ID" )
	@Getter @Setter 
	@EqualsAndHashCode.Include
	private int id;

	@Column( name = "SLOT_NUMBER" )
	@EqualsAndHashCode.Include
	private long slot;

	@OneToOne( targetEntity = Car.class )
	private Car car;

	public long getSlot() {
		return slot;
	}

	public void setSlot(long slot) {
		this.slot = slot;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
