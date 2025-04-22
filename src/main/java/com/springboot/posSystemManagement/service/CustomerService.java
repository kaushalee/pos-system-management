package com.springboot.posSystemManagement.service;

import com.springboot.posSystemManagement.dto.pagination.CustomerPaginationDTO;
import com.springboot.posSystemManagement.dto.request.CustomerRequestDTO;
import com.springboot.posSystemManagement.dto.response.CustomerResponseDTO;
import com.springboot.posSystemManagement.dto.update.CustomerUpdateDTO;

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

