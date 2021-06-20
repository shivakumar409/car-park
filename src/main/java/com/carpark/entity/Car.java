package com.carpark.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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
@Table( name = "CAR" )
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
public class Car {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "ID" )
	@Getter 
	@Setter
	@EqualsAndHashCode.Include
	private int id;

	@Column( name = "CAR_NUMBER", nullable = false )
	@EqualsAndHashCode.Include
	private String carNumber;

	@Column( name = "ID_PROOF" )
	private String idProof;

	@Column( name = "TIME_IN" )
	private LocalDateTime inTime;

	@Column( name = "TIME_OUT" )
	private LocalDateTime outTime;

	@Column( name = "HOURS" )
	private int hours;

	public int getHours() {
		return hours;
	}

	public void setHours( int hours ) {
		this.hours = hours;
	}

	@OneToOne( targetEntity = ParkSlot.class, cascade = CascadeType.ALL )
	private ParkSlot parkSlot;
	
	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber( String carNumber ) {
		this.carNumber = carNumber;
	}

	public LocalDateTime getInTime() {
		return inTime;
	}

	public void setInTime( LocalDateTime inTime ) {
		this.inTime = inTime;
	}

	public LocalDateTime getOutTime() {
		return outTime;
	}

	public void setOutTime( LocalDateTime outTime ) {
		this.outTime = outTime;
	}

	public ParkSlot getParkSlot() {
		return parkSlot;
	}

	public void setParkSlot( ParkSlot parkSlot ) {
		this.parkSlot = parkSlot;
	}

}
