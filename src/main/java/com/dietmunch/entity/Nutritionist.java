package com.dietmunch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nutritionist")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Nutritionist {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String address;

    private String nutritionistName;

    private int maxCost;

    private String phoneNumber;

    private String createDt;

    private String openTimes;

/*    @OneToMany(mappedBy="nutritionist")
    private Set<Appointments> appointmentsList;*/

}
