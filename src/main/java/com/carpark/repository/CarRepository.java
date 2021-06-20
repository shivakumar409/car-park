package com.carpark.repository;

import org.springframework.data.repository.CrudRepository;

import com.carpark.entity.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
	
	Car findByCarNumber(String carNumber);
	
}
