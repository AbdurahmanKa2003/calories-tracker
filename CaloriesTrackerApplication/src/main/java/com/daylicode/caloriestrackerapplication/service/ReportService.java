package com.daylicode.caloriestrackerapplication.service;

import com.daylicode.caloriestrackerapplication.model.Meal;
import com.daylicode.caloriestrackerapplication.model.User;
import com.daylicode.caloriestrackerapplication.repository.MealRepository;
import com.daylicode.caloriestrackerapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public int getTotalCaloriesForDay(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return mealRepository.findByUserAndDate(user, date).stream()
                .mapToInt(Meal::getTotalCalories)
                .sum();
    }

    public boolean isUserWithinCalorieLimit(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        int totalCalories = getTotalCaloriesForDay(userId, date);
        return totalCalories <= user.getDailyCalorieIntake();
    }

    public Map<LocalDate, Integer> getMealHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        List<Meal> meals = mealRepository.findAll();

        return meals.stream()
                .filter(meal -> meal.getUser().equals(user))
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getTotalCalories)));
    }
}
