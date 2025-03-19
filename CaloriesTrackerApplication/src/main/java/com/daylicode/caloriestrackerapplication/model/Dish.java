package com.daylicode.caloriestrackerapplication.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @Min(value = 1, message = "Калорийность должна быть положительным числом")
    private int calories;

    @Min(value = 0, message = "Белки не могут быть отрицательными")
    private double protein;

    @Min(value = 0, message = "Жиры не могут быть отрицательными")
    private double fat;

    @Min(value = 0, message = "Углеводы не могут быть отрицательными")
    private double carbs;
}
