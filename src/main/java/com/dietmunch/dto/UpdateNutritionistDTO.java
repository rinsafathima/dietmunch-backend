package com.dietmunch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNutritionistDTO {

    private int id;

    private String address;

    private String nutritionistName;

    private int maxCost;

    private String phoneNumber;

    private String createDt;

    private String openTimes;

}
