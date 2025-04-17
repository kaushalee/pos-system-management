package com.springboot.customerAndOrderManagementApplication.entity;


import com.springboot.customerAndOrderManagementApplication.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "enmployee")
public class  EmployeeEntity {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;

    @Column(name = "employee_name",length = 100,nullable = false)
    private String employeeName;

    @Column(name = "employee_role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "employee_address",length = 100,nullable = false)
    private String employeeAddress;

    @Column(name = "employee_salary",nullable = false)
    private double employeeSalary;

    @Column(name = "employee_nic",length = 100,nullable = false)
    private String employeeNIC;

    @Column(name = "employee_active_state",columnDefinition = "TINYINT default 1")
    private boolean employeeActiveState;

    @OneToMany(mappedBy="employeeEntity")
    private Set<OderEntity> oderEntitySet;


}
