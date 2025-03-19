package com.daylicode.caloriestrackerapplication.repository;




import com.daylicode.caloriestrackerapplication.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
