package com.springboot.posSystemManagement.service;

import com.springboot.posSystemManagement.dto.pagination.CustomerAndOderDetailsPaginationDTO;
import com.springboot.posSystemManagement.dto.pagination.CustomerAndOderPaginationDTO;
import com.springboot.posSystemManagement.dto.pagination.OderPaginationDTO;
import com.springboot.posSystemManagement.dto.request.OderRequestDTO;
import com.springboot.posSystemManagement.dto.response.OderResponseDTO;

public interface OderService {
    String saveOder(OderRequestDTO oderRequestDTO);

    OderResponseDTO getOderById(int oderId);

    OderPaginationDTO getOderByCustomerId(int customerId, int page);

    CustomerAndOderPaginationDTO getCustomerAndOderByState(boolean activeState, int page, int size);


    String deleteOderById(int oderId);

    CustomerAndOderDetailsPaginationDTO getCustomerAndOderDetailsByOderId(int oderId, int page, int size);

    //OderAndOderDetailsResponseDTO getOderWithOderDetailsById(int oderId);
}
