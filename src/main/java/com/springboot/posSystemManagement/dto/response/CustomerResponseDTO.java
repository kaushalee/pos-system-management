package com.springboot.posSystemManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponseDTO {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerNIC;
    private double customerSalary;
    private boolean customerActiveState;
    // private List<OderRequestDTO> oderRequestDTOList;

}
