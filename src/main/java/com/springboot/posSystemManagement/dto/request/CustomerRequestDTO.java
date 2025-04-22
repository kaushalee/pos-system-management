package com.springboot.posSystemManagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequestDTO {
   // private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerNIC;
    private double customerSalary;
    //private boolean customerActiveState;
   // private List<OderRequestDTO> oderRequestDTOList;

}



