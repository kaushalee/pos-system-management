package com.springboot.posSystemManagement.repo;

import com.springboot.posSystemManagement.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {

    Page<EmployeeEntity> findAllByEmployeeActiveStateEquals(boolean activeState, Pageable pageable);

    long countAllByEmployeeActiveStateEquals(boolean activeState);

    Page<EmployeeEntity> findAllByEmployeeAddressContainsAndEmployeeActiveStateEquals(String employeeAddress, PageRequest of, boolean activeState);

    long countAllByEmployeeAddressContainsAndEmployeeActiveStateEquals(String employeeAddress, boolean activeState);

    Page<EmployeeEntity> findAllByEmployeeSalaryGreaterThanEqualAndEmployeeActiveStateEquals(double employeeSalary, PageRequest of, boolean activeState);

    long countAllByEmployeeSalaryGreaterThanEqualAndEmployeeActiveStateEquals(double employeeSalary, boolean activeState);
}
