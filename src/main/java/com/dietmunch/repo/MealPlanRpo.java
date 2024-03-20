package com.dietmunch.repo;

import com.dietmunch.entity.Appointments;
import com.dietmunch.entity.MealPlan;
import com.dietmunch.entity.Nutritionist;
import com.dietmunch.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MealPlanRpo extends JpaRepository<MealPlan,Integer> {
    List<MealPlan> getByUserId(int userId);


}

