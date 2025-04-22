package com.springboot.posSystemManagement.dto.queryInterface;

public interface CustomerAndOderDetailsResponseInterface {

    //customer
    Integer getCustomerId();
    String getCustomerName();
    String getCustomerAddress();
    //oder details
    Integer getOderDetailsId();
    String getItemName();
    Double getTotal();
}
