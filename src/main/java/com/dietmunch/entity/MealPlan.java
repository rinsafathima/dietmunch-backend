package com.dietmunch.entity;

import com.dietmunch.entity.Nutritionist;
import com.dietmunch.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Entity
@Table(name = "meal_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;
    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Nutritionist nutritionist;
    private String meal_time;
    private String meal_name;
    private String description;
    private String nutritional_information;

}
