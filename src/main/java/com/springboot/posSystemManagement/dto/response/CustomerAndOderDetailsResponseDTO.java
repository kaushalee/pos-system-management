package com.springboot.posSystemManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAndOderDetailsResponseDTO {
    //customer
    private int customerId;
    private String customerName;
    private String customerAddress;
    //oder details
    private int oderDetailsId;
    private String itemName;
    private double total;

}
