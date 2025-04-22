package com.springboot.posSystemManagement.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {

    private int customerId;
    private String customerAddress;
    private double customerSalary;
}
