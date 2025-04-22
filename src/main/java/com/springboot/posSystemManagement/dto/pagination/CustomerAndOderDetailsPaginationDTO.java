package com.springboot.posSystemManagement.dto.pagination;

import com.springboot.posSystemManagement.dto.response.CustomerAndOderDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAndOderDetailsPaginationDTO {

    private List<CustomerAndOderDetailsResponseDTO> customerAndOderDetailsResponseDTOList;
    private long count;
}
