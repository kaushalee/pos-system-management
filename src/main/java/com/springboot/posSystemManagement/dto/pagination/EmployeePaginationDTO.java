package com.springboot.posSystemManagement.dto.pagination;


import java.util.List;

import com.springboot.posSystemManagement.dto.response.EmployeeResponseDTO;
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
