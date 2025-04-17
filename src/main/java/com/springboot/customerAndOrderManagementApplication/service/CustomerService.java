package com.springboot.customerAndOrderManagementApplication.service;

import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.CustomerRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.CustomerResponseDTO;
import com.springboot.customerAndOrderManagementApplication.dto.update.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerRequestDTO customerRequestDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    String deleteCustomer(int customerId);
    
    CustomerResponseDTO getCustomer(int customerId);

    CustomerPaginationDTO getCustomerByCity(String customerAddress, int page, int size);

    CustomerPaginationDTO getCustomerBySalary(double customerSalary, int page);

    CustomerPaginationDTO getAllCustomer(int page, int size);

    CustomerPaginationDTO getAllCustomerWithState(boolean activeState, int page);

    CustomerPaginationDTO getAllCustomerStateIsTrue(int page);

    CustomerPaginationDTO getAll(int page);
}

