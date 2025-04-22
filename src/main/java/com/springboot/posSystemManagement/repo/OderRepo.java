package com.springboot.posSystemManagement.repo;

import com.springboot.posSystemManagement.dto.queryInterface.CustomerAndOderDetailsResponseInterface;
import com.springboot.posSystemManagement.dto.queryInterface.CustomerAndOderResponseInterface;
import com.springboot.posSystemManagement.entity.CustomerEntity;
import com.springboot.posSystemManagement.entity.OderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OderRepo extends JpaRepository<OderEntity, Integer> {
    Page<OderEntity> findAllByCustomerEntityEqualsAndOderActiveStateEquals(CustomerEntity referenceById, Pageable pageable, boolean activeState);

    long countAllByCustomerEntityEqualsAndOderActiveStateEquals(CustomerEntity referenceById, boolean activeState);

    //native query gahanaw kiyal kiyann oni.-api athin gahan query walt
    //query ekak kiyal denna oni
    //default false true karanna oni

//?1 wemuwat false
    @Query(value =
            "select c.customer_id as customerId ," +
                    "c.customer_name as customerName," +
                    "c.customer_address as customerAddress," +
                    "o.oder_id as oderId," +
                    "o.employee_name as employeeName," +
                    "o.net_amount as netAmount " +
                    "from oders o,customer c" +
                    " where o.oder_active_state = ?1 and o.customer_id=c.customer_id",nativeQuery = true) // oder customer id, customer customer id
    List<CustomerAndOderResponseInterface> getCustomerAndOderDetailsByState(boolean activeState, Pageable pageable);

   @Query(value = "select count(*) " +
           "from oders o, customer c " +
           " where o.oder_active_state = ?1 and o.customer_id=c.customer_id", nativeQuery = true)
   long countCustomerAndOderDetailsByState(boolean activeState);

    @Query(value =
            "select c.customer_id as customerId ," +
                    "c.customer_name as customerName," +
                    "c.customer_address as customerAddress," +
                    "od.oder_details_id as oderDetailsId," +
                    "od.item_name as itemName," +
                    "od.total as total " +
                    "from oder_details od,customer c,oders o" +
                    " where od.oder_id=o.oder_id and o.customer_id=c.customer_id",nativeQuery = true) // oder customer id, customer customer id
    List<CustomerAndOderDetailsResponseInterface> getCustomerAndOderDetailsByOderId(int oderId, Pageable pageable);

    @Query(value =
            "select count(*)" +
                    "from oder_details od,customer c,oders o" +
                    " where od.oder_id=o.oder_id and o.customer_id=c.customer_id",nativeQuery = true) // oder customer id, customer customer id
    long countCustomerAndOderDetailsByOderId(int oderId);

}
