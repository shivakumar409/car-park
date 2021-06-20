package com.carpark;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CarParkControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CarParkController controller;

	@Test
	void testCarPark() throws Exception {

		assertNotNull( mockMvc );
		assertThat( controller ).isNotNull();

		JSONObject json = new JSONObject();
		json.put( "carNumber", "KA 32" );
		json.put( "idProof", "SSN");
		json.put( "hours", 4 );
		
		mockMvc.perform( post( "/park" )
				.contentType( MediaType.APPLICATION_JSON )
				.content( json.toString() ) )
						.andExpect( status().isOk() );
	}

	@Test
	void testAdjustTime() throws Exception {
		var carNumber = "KA 32 1234";
		var hours = 4;
		var url = String.format( "/adjustTime/%s/%d", carNumber, hours );

		assertNotNull( mockMvc );
		assertThat( controller ).isNotNull();

		mockMvc.perform(put( url ))
				.andExpect( status().isOk() );
	}

	@Test
	void testLeavePark() throws Exception {
		var hours = 4;
		var url = String.format( "/leave/%s", hours );
		
		assertNotNull( mockMvc );
		assertThat( controller ).isNotNull();

		mockMvc.perform( put( url ) )
				.andExpect( status().isOk() );
	}

}
