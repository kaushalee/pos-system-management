package com.springboot.customerAndOrderManagementApplication.service;

import com.springboot.customerAndOrderManagementApplication.dto.pagination.EmployeePaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.EmployeeRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.EmployeeResponseDTO;
import com.springboot.customerAndOrderManagementApplication.dto.update.EmployeeUpdateDTO;

public interface EmployeeService {
    String saveEmployee(EmployeeRequestDTO employeeRequestDTO);

    String updateEmployee(EmployeeUpdateDTO employeeUpdateDTO);

    String deleteEmployee(int employeeId);

    EmployeeResponseDTO getEmployee(int employeeId);

    EmployeePaginationDTO getAllEmployeeStateIsTrue(int page);

    EmployeePaginationDTO getAllEmployee(int page, int size);

    EmployeePaginationDTO getAllEmployeeWithState(boolean activeState, int page);

    EmployeePaginationDTO getEmployeeByCity(String employeeAddress, int page, int size);

    EmployeePaginationDTO getEmployeeGreaterThanSalary(double employeeSalary, int page);
}
