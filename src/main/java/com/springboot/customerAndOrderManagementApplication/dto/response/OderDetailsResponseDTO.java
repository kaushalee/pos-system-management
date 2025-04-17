package com.springboot.customerAndOrderManagementApplication.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderDetailsResponseDTO {

    private int oderDetailsId;
    private String itemName;
    private double qty;
    private double unitDiscount;
    private double total;
    private int oderEntity;
    private int itemEntity;
}
