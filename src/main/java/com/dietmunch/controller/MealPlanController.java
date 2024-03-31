package com.dietmunch.controller;


import com.dietmunch.dto.AppointmentDTO;
import com.dietmunch.dto.MealPlanDTO;
import com.dietmunch.dto.UpdateNutritionistDTO;
import com.dietmunch.entity.Appointments;
import com.dietmunch.entity.MealPlan;
import com.dietmunch.entity.Nutritionist;
import com.dietmunch.entity.Users;
import com.dietmunch.repo.AppointmentRpo;
import com.dietmunch.repo.MealPlanRpo;
import com.dietmunch.repo.NutritionistRpo;
import com.dietmunch.repo.UserRpo;
import com.dietmunch.utill.StandardResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/mealPlan")
public class MealPlanController {

    @Autowired
    private MealPlanRpo mealPlanRpo;

    @Autowired
    private AppointmentRpo appointmentRpo;

    @Autowired
    private UserRpo userRpo;

    @Autowired
    private NutritionistRpo nutritionistRpo;

    @PostMapping("/create-meal-plan")
    public ResponseEntity<StandardResponse> createMealPlan(@RequestBody MealPlanDTO mealPlanDTO){
        try {

            Nutritionist nutritionist = nutritionistRpo.getById(mealPlanDTO.getNutritionistId());
            Users user = userRpo.getById(mealPlanDTO.getUserId());


            MealPlan mealPlan = new MealPlan();

            mealPlan.setUser(user);
            mealPlan.setNutritionist(nutritionist);
            mealPlan.setId(mealPlanDTO.getId());
            mealPlan.setDescription(mealPlanDTO.getDescription());
            mealPlan.setMeal_name(mealPlanDTO.getMeal_name());
            mealPlan.setMeal_time(mealPlanDTO.getMeal_time());
            mealPlan.setNutritional_information(mealPlanDTO.getNutritional_information());

            mealPlanRpo.save(mealPlan);

            if(mealPlan.getId() > 0) {
                //return user.getName();
                return new ResponseEntity<>(
                        new StandardResponse(200, "Meal plan successfully created", mealPlan.getUser().getName()),
                        HttpStatus.OK);
            } else {
                return null;
            }


        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/get-meal-plan-uid")
    public ResponseEntity<StandardResponse> getAllUserMeals(@RequestParam(value = "uid") int userId){

        List<MealPlan> mealPlanList = mealPlanRpo.getByUserId(userId);

        return new ResponseEntity<>(
                new StandardResponse(200, "Meal Plans",mealPlanList ),
                HttpStatus.OK);
    }

    @PostMapping("/update-meal-plans")
    public ResponseEntity<StandardResponse> updateMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        try {
            MealPlan mealPlanPreInfo = mealPlanRpo.getById(mealPlanDTO.getId());
            mealPlanPreInfo.setMeal_name(mealPlanDTO.getMeal_name());
            mealPlanPreInfo.setMeal_time(mealPlanDTO.getMeal_time());
            mealPlanPreInfo.setDescription(mealPlanDTO.getDescription());
            mealPlanPreInfo.setNutritional_information(mealPlanDTO.getNutritional_information());


            MealPlan updatedData = mealPlanRpo.save(mealPlanPreInfo);

            return new ResponseEntity<>(
                    new StandardResponse(200, "meal plan updated",updatedData ),
                    HttpStatus.OK);

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @DeleteMapping("/delete-meal-plan")
    public ResponseEntity<StandardResponse> deleteMealPlan(@RequestParam (value = "id") int id) {
        try {
            mealPlanRpo.deleteById(id);
            return new ResponseEntity<>(
                    new StandardResponse(200, "Meal plan successfully deleted", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(500, "Error deleting meal plan", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
