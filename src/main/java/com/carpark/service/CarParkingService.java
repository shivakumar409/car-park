package com.carpark.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpark.entity.Car;
import com.carpark.entity.ParkSlot;
import com.carpark.repository.CarRepository;

@Service
public class CarParkingService {

	@Autowired
	private CarRepository carRepository;

	public String park( Car car ) {
		var existingCar = carRepository.findByCarNumber(car.getCarNumber());
		var message = "Car already exist for %s";

		//If car already exist then return message as car already exist
		if ( existingCar == null ) {

			message = "Invalid hours for car %s";

			if ( car.getHours() == 0 )
				car.setHours( 4 );

			if ( car.getHours() > 0 ) {

				car.setInTime( LocalDateTime.now() );
				car.setOutTime( LocalDateTime.now().plusHours( car.getHours() ) ); //out time will be always greater then in time
				
				var parkSlot = new ParkSlot();
				parkSlot.setCar( car );
				parkSlot.setSlot( System.currentTimeMillis() );
				car.setParkSlot( parkSlot );

				carRepository.save( car );

				message = "Car parked sucessfully for %s";
			}
		}

		return String.format( message, car.getCarNumber() );
	}

	public String leave( String carNumber ) {
		var message = "Car Not Found for %s car number";
		var car = carRepository.findByCarNumber( carNumber );

		if ( car != null ) {
			
			carRepository.delete( car );
			message = "Car is ready to leave for %s car number and in time is %s and you are supposed to leave at %s and you are stayed here for %d hours ";
			
			return String.format( message, carNumber, car.getInTime(), car.getOutTime(), car.getHours() );
		}

		return String.format( message, carNumber );
	}

	public String adjustTime( String carNumber, int hours ) {
		var message = "Car Not Found for %s car number we can't accomodate %d hours";
		var car = carRepository.findByCarNumber( carNumber );

		if ( car != null ) {
		
			var outTime = car.getOutTime();
			outTime.plusHours( hours );
			car.setHours( hours );
			carRepository.save( car );
			
			message = "Car time is adjusted for car number %s for hours %d";
			
		}

		return String.format( message, carNumber, hours );
	}
}
