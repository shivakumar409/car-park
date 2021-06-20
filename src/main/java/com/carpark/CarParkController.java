package com.carpark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carpark.entity.Car;
import com.carpark.service.CarParkingService;

@RestController
public class CarParkController {
	@Autowired
	private CarParkingService carParkingService;

	/* The carPark method is used for persisting the car object
	 * 
	 * @method carPark()
	 * 
	 * @param Car
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping( "/park" )
	public ResponseEntity<String> carPark( @RequestBody Car car ) {
		return ResponseEntity.ok( carParkingService.park( car ) );
	}

	/* The adjustTime method is used for modifying time hours for the car object
	 * 
	 * @method adjustTime()
	 * 
	 * @param carNumber, hours
	 * 
	 * @return ResponseEntity
	 */
	@PutMapping( "/adjustTime/{carNumber}/{hours}" )
	public ResponseEntity<String> adjustTime( @PathVariable( value = "carNumber" ) String carNumber,
											  @PathVariable( value = "hours" ) int hours ) {
		
		return ResponseEntity.ok( carParkingService.adjustTime( carNumber, hours ) );
	}
	
	/* The leave method is used for deleting the car object
	 * 
	 * @method adjustTime()
	 * 
	 * @param carNumber
	 * 
	 * @return ResponseEntity
	 */
	@PutMapping( "/leave/{carNumber}" )
	public ResponseEntity<String> takeOut( @PathVariable( value = "carNumber" ) String carNumber ) {
		return ResponseEntity.ok( carParkingService.leave( carNumber ) );
	}
}
