package com.ecs.corporate.cars.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecs.corporate.cars.exception.ResourceNotFoundException;
import com.ecs.corporate.cars.model.Car;
import com.ecs.corporate.cars.repository.CarRepository;

@RestController
public class CarController {
	@Autowired
	private CarRepository carRepository;

	@GetMapping("/cars")
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId)
			throws ResourceNotFoundException {
		
		System.out.println("GET MAPPINGS");

		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
		
		return ResponseEntity.ok().body(car);
	}

	@PostMapping("/cars")
	public Car createCar(@Valid @RequestBody Car car) {
		return carRepository.save(car);
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId,
			@Valid @RequestBody Car carDetails) throws ResourceNotFoundException {
		
		System.out.println("@PutMapping");

		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));

		car.setMake(carDetails.getMake());
		car.setModel(carDetails.getModel());
		car.setColour(carDetails.getColour());
		car.setYear(carDetails.getYear());

		final Car updatedCar = carRepository.save(car);
		return ResponseEntity.ok(updatedCar);
	}

	@DeleteMapping("/cars/{id}")
	public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId)
			throws ResourceNotFoundException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));

		carRepository.delete(car);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
