package com.springboot.customerAndOrderManagementApplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAndOderResponseDTO {

    //customer
    private int customerId;
    private String customerName;
    private String customerAddress;
    //oder
    private int oderId;
    private String employeeName;
    private double netAmount;
    // private int employeeEntity;


}
