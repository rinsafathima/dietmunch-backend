package com.dietmunch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AppointmentDTO {

    private int id;

    private int nutritionistId;

    private String bookingTime;

    private String createdDt;

    private String bookingStatus;

    private String bookingDay;

    private int userId;

}
