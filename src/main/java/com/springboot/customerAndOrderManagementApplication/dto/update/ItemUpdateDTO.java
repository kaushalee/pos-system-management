package com.springboot.customerAndOrderManagementApplication.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemUpdateDTO {

    private int itemId;
    private double itemQty;
    private double itemSupplyPrice;
    private double itemSellPrice;
    private double itemDiscount;
    private double discountWithPercentage;
}
