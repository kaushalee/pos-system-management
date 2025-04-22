package com.springboot.posSystemManagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRequestDTO {

   // private int itemId;
    private String itemName;
    private double itemQty;
    private double itemSupplyPrice;
    private double itemSellPrice;
    private double itemDiscount;
    private double discountWithPercentage;
  //  private boolean itemActiveState;
   //private List<OderDetailsRequestDTO> oderDetailsRequestDTOList;
}
