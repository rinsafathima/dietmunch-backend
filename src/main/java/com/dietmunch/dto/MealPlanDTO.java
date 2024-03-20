package com.dietmunch.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class MealPlanDTO {
    private int id;
    private int userId;
    private int nutritionistId;
    private String meal_name;
    private String meal_time;
    private String description;
    private String nutritional_information;



}
