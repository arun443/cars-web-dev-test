package com.ecs.corporate.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecs.corporate.cars.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
