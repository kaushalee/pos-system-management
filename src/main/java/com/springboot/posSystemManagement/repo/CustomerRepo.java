package com.springboot.posSystemManagement.repo;

import com.springboot.posSystemManagement.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

    Page<CustomerEntity> findAllByCustomerAddressContainsAndCustomerActiveStateEquals(String customerAddress, Pageable pageable, boolean activeState);

    long countAllByCustomerAddressContainsAndCustomerActiveStateEquals(String customerAddress, boolean activeState);

    Page<CustomerEntity> findAllByCustomerSalaryGreaterThanEqualAndCustomerActiveStateEquals(double customerSalary, Pageable pageable, boolean activeState);

    long countAllByCustomerSalaryGreaterThanEqualAndCustomerActiveStateEquals(double customerSalary, boolean activeState);

    Page<CustomerEntity> findAllByCustomerActiveStateEquals(boolean activeState, Pageable pageable);

    long countAllByCustomerActiveStateEquals(boolean activeState);
}
