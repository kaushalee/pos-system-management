package com.springboot.customerAndOrderManagementApplication.util.mappers;

import com.springboot.customerAndOrderManagementApplication.dto.request.EmployeeRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.EmployeeResponseDTO;
import com.springboot.customerAndOrderManagementApplication.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")

public interface EmployeeMapper {


    EmployeeEntity employeeRequestDTOToEmployeeEntity(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO employeeEntityToEmployeeResponseDTO(EmployeeEntity employeeEntity);

    List<EmployeeResponseDTO> employeeEntityPageToEmployeeResponseDTOList(Page<EmployeeEntity> employeeEntityPage);
}
