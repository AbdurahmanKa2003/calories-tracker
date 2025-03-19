package com.daylicode.caloriestrackerapplication.controller;

import com.daylicode.caloriestrackerapplication.model.Meal;
import com.daylicode.caloriestrackerapplication.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<Meal>> getMealsByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        return ResponseEntity.ok(mealService.getMealsByUserAndDate(userId, LocalDate.parse(date)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody @Valid Meal meal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mealService.createMeal(meal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}
