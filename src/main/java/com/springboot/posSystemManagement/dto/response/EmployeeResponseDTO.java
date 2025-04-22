package com.springboot.posSystemManagement.dto.response;


import com.springboot.posSystemManagement.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponseDTO {

    private int employeeId;
    private String employeeName;
    private Role role;
    private String employeeAddress;
    private double employeeSalary;
    private String employeeNIC;
    private boolean employeeActiveState;
    //private List<OderResponseDTO> oderResponseDTOList;
}
