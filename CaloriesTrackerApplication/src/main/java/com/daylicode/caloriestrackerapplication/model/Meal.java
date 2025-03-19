package com.daylicode.caloriestrackerapplication.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Прием пищи должен быть привязан к пользователю")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "meal_dishes",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    @NotNull(message = "Список блюд не может быть пустым")
    private List<Dish> dishes;

    private LocalDate date;

    private int totalCalories;

    @PrePersist
    @PreUpdate
    private void calculateTotalCalories() {
        totalCalories = dishes.stream().mapToInt(Dish::getCalories).sum();
    }
}
