package com.dietmunch.repo;


import com.dietmunch.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AppointmentRpo extends JpaRepository<Appointments,Integer> {

    List<Appointments> getByUserId(int userId);
    void deleteById(Integer id);
}
