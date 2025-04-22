package com.springboot.posSystemManagement.service.serviceIMPL;


import com.springboot.posSystemManagement.dto.pagination.EmployeePaginationDTO;
import com.springboot.posSystemManagement.dto.request.EmployeeRequestDTO;
import com.springboot.posSystemManagement.dto.response.EmployeeResponseDTO;
import com.springboot.posSystemManagement.dto.update.EmployeeUpdateDTO;
import com.springboot.posSystemManagement.entity.EmployeeEntity;
import com.springboot.posSystemManagement.exception.NotFoundException;
import com.springboot.posSystemManagement.repo.EmployeeRepo;
import com.springboot.posSystemManagement.service.EmployeeService;
import com.springboot.posSystemManagement.util.mappers.EmployeeMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class EmployeeServiceIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public String saveEmployee(EmployeeRequestDTO employeeRequestDTO) {

        // request walin set<oder enttiy> awanne nathi nisa aulalk na
        //ewana data tika aulkl nathuwa set wenaw
         EmployeeEntity employeeEntity = employeeMapper.employeeRequestDTOToEmployeeEntity(employeeRequestDTO);

        employeeRepo.save(employeeEntity);
        return employeeRequestDTO.getEmployeeName().concat("is saved.");
    }

    @Override
    public String updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {

        if (employeeRepo.existsById(employeeUpdateDTO.getEmployeeId())) {
            EmployeeEntity employeeEntity = employeeRepo.getReferenceById(employeeUpdateDTO.getEmployeeId());
            employeeEntity.setRole(employeeUpdateDTO.getRole());
            employeeEntity.setEmployeeAddress(employeeUpdateDTO.getEmployeeAddress());
            employeeEntity.setEmployeeSalary(employeeUpdateDTO.getEmployeeSalary());

            employeeRepo.save(employeeEntity);
            return employeeUpdateDTO.getEmployeeId() + " is updated";
        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public String deleteEmployee(int employeeId) {
        if (employeeRepo.existsById(employeeId)) {

            EmployeeEntity employeeEntity= employeeRepo.getReferenceById(employeeId);
            employeeRepo.delete(employeeEntity);
            return employeeId + " is deleted.";
        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeeResponseDTO getEmployee(int employeeId) {
        if (employeeRepo.existsById(employeeId)) {

            EmployeeEntity employeeEntity= employeeRepo.getReferenceById(employeeId);
            EmployeeResponseDTO employeeResponseDTO = employeeMapper.employeeEntityToEmployeeResponseDTO(employeeEntity);
            return employeeResponseDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeePaginationDTO getAllEmployeeStateIsTrue(int page) {
        boolean activeState = true;
        int size = 3;
        Page<EmployeeEntity> employeeEntityPage = employeeRepo.findAllByEmployeeActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = employeeRepo.countAllByEmployeeActiveStateEquals(activeState);
        if (count > 0) {//employeeEntityPage.SIze()>0

            List<EmployeeResponseDTO> employeeResponseDTOList = employeeMapper.employeeEntityPageToEmployeeResponseDTOList(employeeEntityPage);
            EmployeePaginationDTO employeePaginationDTO = new EmployeePaginationDTO(
                    employeeResponseDTOList, count
            );
            return employeePaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeePaginationDTO getAllEmployee(int page, int size) {
        Page<EmployeeEntity> employeeEntityPage = employeeRepo.findAll(PageRequest.of(page, size));
        long count = employeeRepo.count();
        if (count > 0) {//employeeEntityPage.SIze()>0

            List<EmployeeResponseDTO> employeeResponseDTOList =employeeMapper.employeeEntityPageToEmployeeResponseDTOList(employeeEntityPage) ;
            EmployeePaginationDTO employeePaginationDTO = new EmployeePaginationDTO(
                    employeeResponseDTOList, count
            );
            return employeePaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeePaginationDTO getAllEmployeeWithState(boolean activeState, int page) {
        int size = 3;
        Page<EmployeeEntity> employeeEntityPage = employeeRepo.findAllByEmployeeActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = employeeRepo.countAllByEmployeeActiveStateEquals(activeState);
        if (count > 0) {//employeeEntityPage.SIze()>0

            List<EmployeeResponseDTO> employeeResponseDTOList = employeeMapper.employeeEntityPageToEmployeeResponseDTOList(employeeEntityPage);
            EmployeePaginationDTO employeePaginationDTO = new EmployeePaginationDTO(
                    employeeResponseDTOList, count
            );
            return employeePaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeePaginationDTO getEmployeeByCity(String employeeAddress, int page, int size) {
        boolean activeState = true;
        Page<EmployeeEntity> employeeEntityPage = employeeRepo.findAllByEmployeeAddressContainsAndEmployeeActiveStateEquals(employeeAddress, PageRequest.of(page, size), activeState);
        long count = employeeRepo.countAllByEmployeeAddressContainsAndEmployeeActiveStateEquals(employeeAddress, activeState);
        if (count > 0) {    //employeeEntityPage.size()>0

            List<EmployeeResponseDTO> employeeResponseDTOList = employeeMapper.employeeEntityPageToEmployeeResponseDTOList(employeeEntityPage);
            EmployeePaginationDTO employeePaginationDTO = new EmployeePaginationDTO(
                    employeeResponseDTOList, count
            );
            return employeePaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public EmployeePaginationDTO getEmployeeGreaterThanSalary(double employeeSalary, int page) {
        boolean activeState = true;
        int size = 3;
        Page<EmployeeEntity> employeeEntityPage = employeeRepo.findAllByEmployeeSalaryGreaterThanEqualAndEmployeeActiveStateEquals(employeeSalary, PageRequest.of(page, size), activeState);
        long count = employeeRepo.countAllByEmployeeSalaryGreaterThanEqualAndEmployeeActiveStateEquals(employeeSalary, activeState);
        if (count > 0) {//employeeEntityPage.SIze()>0
            List<EmployeeResponseDTO> employeeResponseDTOList =employeeMapper.employeeEntityPageToEmployeeResponseDTOList(employeeEntityPage );
            EmployeePaginationDTO employeePaginationDTO = new EmployeePaginationDTO(
                    employeeResponseDTOList, count
            );
            System.out.println(employeeEntityPage.getSize());
            return employeePaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }
}
