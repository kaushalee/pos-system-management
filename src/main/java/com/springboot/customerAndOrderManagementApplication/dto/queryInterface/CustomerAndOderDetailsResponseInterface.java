package com.springboot.customerAndOrderManagementApplication.dto.queryInterface;

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
