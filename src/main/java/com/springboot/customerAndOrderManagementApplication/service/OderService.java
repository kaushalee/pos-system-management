package com.springboot.customerAndOrderManagementApplication.service;

import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerAndOderDetailsPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerAndOderPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.pagination.OderPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.OderRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.OderResponseDTO;

public interface OderService {
    String saveOder(OderRequestDTO oderRequestDTO);

    OderResponseDTO getOderById(int oderId);

    OderPaginationDTO getOderByCustomerId(int customerId, int page);

    CustomerAndOderPaginationDTO getCustomerAndOderByState(boolean activeState, int page, int size);


    String deleteOderById(int oderId);

    CustomerAndOderDetailsPaginationDTO getCustomerAndOderDetailsByOderId(int oderId, int page, int size);

    //OderAndOderDetailsResponseDTO getOderWithOderDetailsById(int oderId);
}
