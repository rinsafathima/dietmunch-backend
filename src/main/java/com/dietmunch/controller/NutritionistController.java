package com.dietmunch.controller;

import com.dietmunch.dto.UpdateNutritionistDTO;
import com.dietmunch.entity.Nutritionist;
import com.dietmunch.repo.NutritionistRpo;
import com.dietmunch.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/nutritionist")
public class NutritionistController {

    @Autowired
    private NutritionistRpo nutritionistRpo;

    @PostMapping("/register-nutritionist")
    public ResponseEntity<StandardResponse> registerNutritionist(@RequestBody Nutritionist nutritionist){
        try {
            List<Nutritionist> prevNutritionist = nutritionistRpo.findAllByNutritionistName(nutritionist.getNutritionistName());

            if(prevNutritionist.isEmpty()) {
                nutritionist.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
                nutritionistRpo.save(nutritionist);

                if(nutritionist.getId() > 0) {
                    //return user.getName();
                    return new ResponseEntity<>(
                            new StandardResponse(200, "Registration successful", nutritionist.getNutritionistName()),
                            HttpStatus.OK);
                } else {
                    return null;
                }
            } else {
                //return "User is already registered";
                return new ResponseEntity<>(
                        new StandardResponse(404, "User is already registered", null),
                        HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/update-nutritionist-profile")
    public ResponseEntity<StandardResponse> updateNutritionist(@RequestBody UpdateNutritionistDTO updateNutritionistDTO) {
        try {
            Nutritionist nutritionistPreInfo = nutritionistRpo.getById(updateNutritionistDTO.getId());
            nutritionistPreInfo.setNutritionistName(updateNutritionistDTO.getNutritionistName());
            nutritionistPreInfo.setAddress(updateNutritionistDTO.getAddress());
            nutritionistPreInfo.setMaxCost(updateNutritionistDTO.getMaxCost());
            nutritionistPreInfo.setOpenTimes(updateNutritionistDTO.getOpenTimes());
            nutritionistPreInfo.setPhoneNumber(updateNutritionistDTO.getPhoneNumber());

             Nutritionist updatedData = nutritionistRpo.save(nutritionistPreInfo);

            return new ResponseEntity<>(
                    new StandardResponse(200, "updated",updatedData ),
                    HttpStatus.OK);

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @DeleteMapping("/delete-nutritionist/{id}")
    public ResponseEntity<StandardResponse> deleteNutritionist(@PathVariable Integer id) {
        try {
            if (nutritionistRpo.existsById(id)) {
                nutritionistRpo.deleteById(id);
                return new ResponseEntity<>(
                        new StandardResponse(200, "Nutritionist deleted successfully", null),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new StandardResponse(404, "Nutritionist not found", null),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
