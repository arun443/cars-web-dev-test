package com.ecs.corporate.cars.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.ecs.corporate.cars.Application;
import com.ecs.corporate.cars.model.Car;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllCars() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cars",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetCarById() {
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/1", Car.class);
		System.out.println(car.getMake());
		System.out.println(car.getModel());
		System.out.println(car.getColour());
		System.out.println(car.getYear());



		assertNotNull(car);
	}

	@Test
	public void testCreateCar() {
		Car car = new Car();
		car.setMake("VOLVO");
		car.setModel("S40");
		car.setColour("Black");
		car.setYear("2017");


		ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/cars", car, Car.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateCar() {
		int id = 1;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		car.setMake("S80");
		car.setColour("BLUE");


		restTemplate.put(getRootUrl() + "/cars/" + id, car);

		Car updatedCar = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		assertNotNull(updatedCar);
	}

	@Test
	public void testDeleteCar() {
		int id = 2;
		Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		assertNotNull(car);

		restTemplate.delete(getRootUrl() + "/cars/" + id);

		try {
			car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
