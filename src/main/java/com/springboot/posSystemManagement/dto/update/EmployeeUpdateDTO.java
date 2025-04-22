package com.springboot.posSystemManagement.dto.update;


import com.springboot.posSystemManagement.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeUpdateDTO {

    private int employeeId;
    private Role role;
    private String employeeAddress;
    private double employeeSalary;
}
