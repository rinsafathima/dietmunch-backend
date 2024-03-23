package com.dietmunch.repo;

import com.dietmunch.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NutritionistRpo extends JpaRepository<Nutritionist,Integer> {

    List<Nutritionist> findAllByNutritionistName(String nutritionistName);
    void deleteById(Integer id);


}
