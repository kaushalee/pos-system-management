package com.springboot.customerAndOrderManagementApplication.dto.update;


import com.springboot.customerAndOrderManagementApplication.entity.enums.Role;
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
