
package com.tejo.appdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TEJO")
@Data
public class TejoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;


    @Column(name = "EMAIL", length = 150, unique = true, nullable = true)
    private String email;

    @Column(name = "JOINING_DATE")
    private LocalDate joiningDate;

    @Column(name = "EXPERIENCE_YEARS", precision = 4, scale = 1)
    private BigDecimal experienceYears;


    @Column(name = "SALARY", precision = 10, scale = 2)
    private BigDecimal salary;


    @Column(name = "DEPARTMENT", length = 50)
    private String department = "IT";


    @Column(name = "IS_ACTIVE")
    private Boolean isActive = true;

    // Optionally, you can add a validation annotation for salary if you want runtime validation
    // @DecimalMin(value = "0.01", message = "Salary must be greater than 0")
}
