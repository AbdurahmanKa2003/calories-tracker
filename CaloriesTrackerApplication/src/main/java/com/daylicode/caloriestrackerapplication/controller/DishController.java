package com.daylicode.caloriestrackerapplication.controller;



import com.daylicode.caloriestrackerapplication.model.Dish;
import com.daylicode.caloriestrackerapplication.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody @Valid Dish dish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dishService.createDish(dish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody @Valid Dish dish) {
        return ResponseEntity.ok(dishService.updateDish(id, dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
