package com.springboot.customerAndOrderManagementApplication.repo;

import com.springboot.customerAndOrderManagementApplication.entity.OderDetailsEntity;
import com.springboot.customerAndOrderManagementApplication.entity.OderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OderDetailsRepo extends JpaRepository<OderDetailsEntity, Integer> {

    List<OderDetailsEntity> findAllByOderEntityEquals(OderEntity referenceById);


    // List<OderDetailsEntity> findAllByOderEntityEquals(OderEntity oderEntity);

  //  List<CustomerAndOderResponseDTO> pToCustomerAndOderResponseDTOList(List<CustomerAndOderResponseInterface> p);
}
