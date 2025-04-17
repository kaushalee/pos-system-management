package com.springboot.customerAndOrderManagementApplication.dto.pagination;

import com.springboot.customerAndOrderManagementApplication.dto.response.CustomerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPaginationDTO {

    private List<CustomerResponseDTO> customerResponseDTOList;
    private long count;
}
