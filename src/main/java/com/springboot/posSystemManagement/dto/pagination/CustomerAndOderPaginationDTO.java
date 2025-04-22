package com.springboot.posSystemManagement.dto.pagination;

import com.springboot.posSystemManagement.dto.response.CustomerAndOderResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAndOderPaginationDTO {

    private List<CustomerAndOderResponseDTO> customerAndOderResponseDTOList;
    private long count;
}
