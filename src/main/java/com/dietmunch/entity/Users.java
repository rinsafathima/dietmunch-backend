package com.dietmunch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String email;

    private String pwd;

    private int age;

    @Column(name ="mobile_number")
    private String mobileNumber;

    private String address;

    private String role;

    @Column(name = "create_dt")
    private String createDt;



/*    @OneToMany(mappedBy="user")
    private Set<Appointments> appointmentsList;*/
}
