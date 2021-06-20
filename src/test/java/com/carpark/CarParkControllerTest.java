package com.carpark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.carpark.entity.Car;

@ExtendWith(MockitoExtension.class)
class CarParkControllerTest {

	private CarParkController controller;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
		controller = mock(CarParkController.class);
	}

	@Test
	void testCarPark() {
		var expected = "Car parked sucessfully for KA 32 1234";
		var car = new Car();

		car.setIdProof( "N/A" );
		car.setCarNumber( "KA 32 1234" );
		car.setInTime( LocalDateTime.now() );
		car.setOutTime( LocalDateTime.now().plusHours( 4 ) );
		
		when( controller.carPark( car ) ).thenReturn( ResponseEntity.ok( expected ) );
		
		var actual = controller.carPark( car );
		
		verify( controller, timeout( 1 ) ).carPark( car );
		
		assertEquals( ResponseEntity.ok( expected ), actual );
	}

	@Test
	void testAdjustTiming() {
		var carNumber = "KA 32 1234";
		var hours = 3;
		var expected = String.format( "Car time is adjusted for car number %s for hours %d", carNumber, hours );
		
		when( controller.adjustTime( carNumber, hours ) ).thenReturn( ResponseEntity.ok( expected ) );
		
		var actual = controller.adjustTime( carNumber, hours );
		
		assertEquals( ResponseEntity.ok( expected ), actual );
		
		verify( controller, timeout( 1 ) ).adjustTime( carNumber, hours );
	}

	@Test
	void testLeave() {
		var carNumber = "KA 32 1234";
		var hours = 3;
		var inTime = LocalDateTime.now().minusHours( hours );
		var outTime = inTime.plusHours( hours );
		var expectedMessage = "Car is ready to leave for %s car number and in time is %s and you are supposed to leave at %s and you are stayed here for %d hours ";
		var expected = String.format( expectedMessage, carNumber, inTime, outTime, hours );
		
		when( controller.takeOut( carNumber ) ).thenReturn( ResponseEntity.ok( expected ) );
		
		var actual = controller.takeOut( carNumber );
		
		assertEquals( ResponseEntity.ok( expected ), actual );
		
		verify( controller, timeout(1) ).takeOut( carNumber );
	}
}
