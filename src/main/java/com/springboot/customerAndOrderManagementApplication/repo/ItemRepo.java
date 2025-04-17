package com.springboot.customerAndOrderManagementApplication.repo;

import com.springboot.customerAndOrderManagementApplication.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<ItemEntity, Integer> {


    long countAllByItemNameContainsAndItemActiveStateEquals(String itemName, boolean activeState);

    Page<ItemEntity> findAllByItemNameContainsAndItemActiveStateEquals(String itemName, PageRequest of, boolean activeState);

    Page<ItemEntity> findAllByDiscountWithPercentageLessThanAndItemActiveStateEquals(double itemDiscountPercentage,Pageable pageable, boolean activeState);

    long countAllByDiscountWithPercentageLessThanAndItemActiveStateEquals(double itemDiscountPercentage, boolean activeState);

    Page<ItemEntity> findAllByItemActiveStateEquals(boolean activeState, Pageable pageable);

    long countAllByItemActiveStateEquals(boolean activeState);

}
