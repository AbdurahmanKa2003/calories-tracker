package com.daylicode.caloriestrackerapplication.service;

import com.daylicode.caloriestrackerapplication.model.Meal;
import com.daylicode.caloriestrackerapplication.model.User;
import com.daylicode.caloriestrackerapplication.repository.MealRepository;
import com.daylicode.caloriestrackerapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    void getMealsByUserAndDate_UserExists_ReturnsMeals() {
        User user = new User();
        user.setId(1L);
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setDate(LocalDate.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserAndDate(user, LocalDate.now())).thenReturn(List.of(meal));

        List<Meal> meals = mealService.getMealsByUserAndDate(1L, LocalDate.now());

        assertFalse(meals.isEmpty());
        assertEquals(1, meals.size());
    }

    @Test
    void getMealsByUserAndDate_UserNotFound_ThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                mealService.getMealsByUserAndDate(1L, LocalDate.now()));

        assertEquals("Пользователь не найден", exception.getMessage());
    }
}
