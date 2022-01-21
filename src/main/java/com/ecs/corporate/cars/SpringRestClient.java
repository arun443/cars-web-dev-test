package com.ecs.corporate.cars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ecs.corporate.cars.model.Car;

public class SpringRestClient {

	private static final String GET_CARS_ENDPOINT_URL = "http://localhost:8080/cars";
	private static final String GET_CAR_ENDPOINT_URL = "http://localhost:8080/cars/{id}";
	private static final String CREATE_CAR_ENDPOINT_URL = "http://localhost:8080/cars";
	private static final String UPDATE_CAR_ENDPOINT_URL = "http://localhost:8080/cars/{id}";
	private static final String DELETE_CAR_ENDPOINT_URL = "http://localhost:8080/cars/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		//  first create a new car
		springRestClient.createCar();
		
	       // get new created car from step1
		springRestClient.getCarById();
		
		// Step3: get all cars
		//springRestClient.getCars();
		
		// Update car with id = 1
		//springRestClient.updateCar();
		
		//  Delete car with id = 1
		//springRestClient.deleteCar();
	}

	private void getCars() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_CARS_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getCarById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Car result = restTemplate.getForObject(GET_CAR_ENDPOINT_URL, Car.class, params);

		System.out.println(result);
	}

	private void createCar() {

		Car newCar= new Car("Jaguar", "X-TYPE", "white","2021");

		RestTemplate restTemplate = new RestTemplate();
		Car result = restTemplate.postForObject(CREATE_CAR_ENDPOINT_URL, newCar, Car.class);

		System.out.println(result);
	}

	private void updateCar() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Car updatedCar = new Car("Jaguar", "Y-TYPE", "silver","2019");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_CAR_ENDPOINT_URL, updatedCar, params);
	}

	private void deleteCar() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_CAR_ENDPOINT_URL, params);
	}
}
