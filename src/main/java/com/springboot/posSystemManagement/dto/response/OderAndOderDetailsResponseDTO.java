package com.springboot.posSystemManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderAndOderDetailsResponseDTO {

    private int oderId;
    private String customerName;
    private String employeeName;
    private Date data;
    private double grossAmount;
    private double totalDiscount;
    private double netAmount;
    private double giftVoucher;
    private double cash;
    private double totalCharge;
    private int noOfItem;
    private boolean oderActiveState;
    private int customerEntity;
    private List<OderDetailsResponseDTO> oderDetailsResponseDTOList;
    private int employeeEntity;
}


