package com.springboot.customerAndOrderManagementApplication.dto.pagination;


import java.util.List;

import com.springboot.customerAndOrderManagementApplication.dto.response.EmployeeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePaginationDTO {

    private List<EmployeeResponseDTO> employeeResponseDTOList;
    private long count;
}
