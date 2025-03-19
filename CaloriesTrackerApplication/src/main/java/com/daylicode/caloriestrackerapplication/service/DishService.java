package com.daylicode.caloriestrackerapplication.service;

import com.daylicode.caloriestrackerapplication.model.Dish;
import com.daylicode.caloriestrackerapplication.repository.DishRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Блюдо не найдено"));
    }

    public Dish createDish(@Valid Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, @Valid Dish dishDetails) {
        Dish dish = getDishById(id);
        dish.setName(dishDetails.getName());
        dish.setCalories(dishDetails.getCalories());
        dish.setProtein(dishDetails.getProtein());
        dish.setFat(dishDetails.getFat());
        dish.setCarbs(dishDetails.getCarbs());
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        Dish dish = getDishById(id);
        dishRepository.delete(dish);
    }
}
