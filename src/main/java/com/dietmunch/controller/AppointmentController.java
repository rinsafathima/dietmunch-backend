package com.dietmunch.controller;

import com.dietmunch.dto.AppointmentDTO;
import com.dietmunch.dto.UpdateNutritionistDTO;
import com.dietmunch.entity.Appointments;
import com.dietmunch.entity.Nutritionist;
import com.dietmunch.entity.Users;
import com.dietmunch.repo.AppointmentRpo;
import com.dietmunch.repo.NutritionistRpo;
import com.dietmunch.repo.UserRpo;
import com.dietmunch.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRpo appointmentRpo;

    @Autowired
    private AppointmentRpo A;
    @Autowired
    private UserRpo userRpo;

    @Autowired
    private NutritionistRpo nutritionistRpo;

    @PostMapping("/set-appointment")
    public ResponseEntity<StandardResponse> setAppointment(@RequestBody AppointmentDTO appointmentDTO){
        try {
            Nutritionist nutritionist = nutritionistRpo.getById(appointmentDTO.getNutritionistId());
            Users user = userRpo.getById(appointmentDTO.getUserId());

            Appointments appointments = new Appointments();

            appointments.setCreatedDt(String.valueOf(new Date(System.currentTimeMillis())));
            appointments.setUser(user);
            appointments.setNutritionist(nutritionist);
            appointments.setBookingDay(appointmentDTO.getBookingDay());
            appointments.setBookingTime(appointmentDTO.getBookingTime());
            appointments.setBookingStatus(appointmentDTO.getBookingStatus());

            appointmentRpo.save(appointments);

                if(appointments.getId() > 0) {
                    //return user.getName();
                    return new ResponseEntity<>(
                            new StandardResponse(200, "appointments successful", appointments.getUser().getName()),
                            HttpStatus.OK);
                } else {
                    return null;
                }


        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/update-appointments")
    public ResponseEntity<StandardResponse> updateAppointments(@RequestBody AppointmentDTO appointmentDTO) {

        try {
            Nutritionist nutritionist = nutritionistRpo.getById(appointmentDTO.getNutritionistId());
            Users user = userRpo.getById(appointmentDTO.getUserId());
            Appointments appointmentsPreInfo = appointmentRpo.getById(appointmentDTO.getId());
            //appointmentsPreInfo.setNutritionist(nutritionist);
            appointmentsPreInfo.setBookingTime(appointmentDTO.getBookingTime());
            appointmentsPreInfo.setBookingStatus(appointmentDTO.getBookingStatus());
            appointmentsPreInfo.setBookingDay(appointmentDTO.getBookingDay());
            //appointmentsPreInfo.setUser(user);

            Appointments updatedData = appointmentRpo.save(appointmentsPreInfo);

            return new ResponseEntity<>(
                    new StandardResponse(200, "updated",updatedData ),
                    HttpStatus.OK);

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

        @GetMapping("/get-appointment-uid")
    public ResponseEntity<StandardResponse> getAllUserAppointments(@RequestParam(value = "uid") int userId){

        List<Appointments> appointmentsList = appointmentRpo.getByUserId(userId);

        return new ResponseEntity<>(
                new StandardResponse(200, "All appointments",appointmentsList ),
                HttpStatus.OK);
    }
}





















