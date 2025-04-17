package com.springboot.customerAndOrderManagementApplication.dto.pagination;

import com.springboot.customerAndOrderManagementApplication.dto.response.CustomerAndOderResponseDTO;
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
