package com.springboot.posSystemManagement.dto.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDTO {

    private int itemId;
    private String itemName;
    private double itemQty;
    private double itemSupplyPrice;
    private double itemSellPrice;
    private double itemDiscount;
    private double discountWithPercentage;
    private boolean itemActiveState;
    //private List<OderDetailsResponseDTO> oderDetailsResponseDTOList;
}
