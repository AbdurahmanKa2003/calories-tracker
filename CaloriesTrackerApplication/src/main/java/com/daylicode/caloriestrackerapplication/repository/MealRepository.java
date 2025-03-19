package com.daylicode.caloriestrackerapplication.repository;

import com.daylicode.caloriestrackerapplication.model.Meal;
import com.daylicode.caloriestrackerapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndDate(User user, LocalDate date);
}
