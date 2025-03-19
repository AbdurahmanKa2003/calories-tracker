package com.daylicode.caloriestrackerapplication.model;




import com.daylicode.caloriestrackerapplication.enums.Goal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @Min(value = 10, message = "Возраст должен быть не менее 10 лет")
    @Max(value = 120, message = "Возраст должен быть не более 120 лет")
    private int age;

    @Min(value = 30, message = "Вес должен быть не менее 30 кг")
    @Max(value = 300, message = "Вес должен быть не более 300 кг")
    private double weight;

    @Min(value = 100, message = "Рост должен быть не менее 100 см")
    @Max(value = 250, message = "Рост должен быть не более 250 см")
    private double height;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Цель должна быть указана")
    private Goal goal;

    private double dailyCalories;

    @PrePersist
    @PreUpdate
    private void calculateDailyCalories() {
        double bmr;
        if (age >= 18) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        switch (goal) {
            case WEIGHT_LOSS -> dailyCalories = bmr * 0.8;
            case MAINTENANCE -> dailyCalories = bmr;
            case WEIGHT_GAIN -> dailyCalories = bmr * 1.2;
        }
    }

    public Double getDailyCalorieIntake() {
        return  dailyCalories;
    }
}

