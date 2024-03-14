package com.dietmunch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointments {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name="nutritionist_id", nullable = false)
    private Nutritionist nutritionist;

    private String bookingTime;

    private String createdDt;

    private String bookingStatus;

    private String bookingDay;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;
}
