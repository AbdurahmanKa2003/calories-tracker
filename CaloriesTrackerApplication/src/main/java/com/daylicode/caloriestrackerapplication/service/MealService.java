package com.daylicode.caloriestrackerapplication.service;




import com.daylicode.caloriestrackerapplication.model.Meal;
import com.daylicode.caloriestrackerapplication.model.User;
import com.daylicode.caloriestrackerapplication.repository.MealRepository;
import com.daylicode.caloriestrackerapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public List<Meal> getMealsByUserAndDate(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return mealRepository.findByUserAndDate(user, date);
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Прием пищи не найден"));
    }

    public Meal createMeal(@Valid Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        Meal meal = getMealById(id);
        mealRepository.delete(meal);
    }
}
