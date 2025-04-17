package com.springboot.customerAndOrderManagementApplication.dto.request;


import com.springboot.customerAndOrderManagementApplication.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDTO {
   // private int employeeId;
    private String employeeName;
    private Role role;
    private String employeeAddress;
    private double employeeSalary;
    private String employeeNIC;
   // private boolean employeeActiveState;
  //private List<OderRequestDTO> oderRequestDTOList;

}
