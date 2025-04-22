package com.springboot.posSystemManagement.service.serviceIMPL;

import com.springboot.posSystemManagement.dto.pagination.CustomerAndOderDetailsPaginationDTO;
import com.springboot.posSystemManagement.dto.pagination.CustomerAndOderPaginationDTO;
import com.springboot.posSystemManagement.dto.pagination.OderPaginationDTO;
import com.springboot.posSystemManagement.dto.queryInterface.CustomerAndOderDetailsResponseInterface;
import com.springboot.posSystemManagement.dto.queryInterface.CustomerAndOderResponseInterface;
import com.springboot.posSystemManagement.dto.request.OderDetailsRequestDTO;
import com.springboot.posSystemManagement.dto.request.OderRequestDTO;
import com.springboot.posSystemManagement.dto.response.CustomerAndOderDetailsResponseDTO;
import com.springboot.posSystemManagement.dto.response.CustomerAndOderResponseDTO;
import com.springboot.posSystemManagement.dto.response.OderResponseDTO;
import com.springboot.posSystemManagement.entity.OderDetailsEntity;
import com.springboot.posSystemManagement.entity.OderEntity;
import com.springboot.posSystemManagement.exception.NotFoundException;
import com.springboot.posSystemManagement.repo.*;
import com.springboot.posSystemManagement.service.OderService;
import com.springboot.posSystemManagement.util.mappers.OderMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional

public class OderServiceIMPL implements OderService {

    @Autowired
    private OderRepo oderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OderDetailsRepo oderDetailsRepo;

    @Autowired
    private OderMapper oderMapper;


    /*************
     * //no error
     //mapper waladi------->   request yawaddi
     //            1)oder entity eke ( set<oder details> / customer entity /employee entity ) data giye na. passe aran set kara
     //            2)oder details list eke (oder entity / item entity) data giye na .passe for loop ekakin set kala
     *******/

    @Transactional
    @Override
    public String saveOder(OderRequestDTO oderRequestDTO) {

        //data oder details ekat set karanne na thma (set<OderDetailsEntity>)
        OderEntity oderEntity = modelMapper.map(oderRequestDTO, OderEntity.class);
        //entity ekat data kotasak gihin kotasak na ( customer entity,employee entity) eka entity ekakat
        oderEntity.setCustomerEntity(customerRepo.getReferenceById(oderRequestDTO.getCustomerEntity()));
        oderEntity.setEmployeeEntity(employeeRepo.getReferenceById(oderRequestDTO.getEmployeeEntity()));
        //save denaw oder entity ekat
        oderRepo.save(oderEntity);

        //oder eka save una kiyal check karal oder details ekat data yawanaw
        if (oderRepo.existsById(oderEntity.getOderId())) {

            List<OderDetailsRequestDTO> oderDetailsRequestDTOList = oderRequestDTO.getOderDetailsRequestDTOList();
            List<OderDetailsEntity> oderDetailsEntityList = modelMapper.map(oderDetailsRequestDTOList, new TypeToken<List<OderDetailsEntity>>() {
            }.getType());

            //entity list ekat kotasak gihin kotasak na(oder entity,item entity) entity list ekakt
            for (int i = 0; i < oderDetailsRequestDTOList.size(); i++) {
                oderDetailsEntityList.get(i).setOderEntity(oderEntity);
                oderDetailsEntityList.get(i).setItemEntity(itemRepo.getReferenceById(oderRequestDTO.getOderDetailsRequestDTOList().get(i).getItemEntity()));
            }

            //oder details ekat data giyad kiyal check karanaw
            if (oderDetailsEntityList.size() > 0) {
                //save All denaw oder details ekat
                oderDetailsRepo.saveAll(oderDetailsEntityList);
            } else {
                throw new RuntimeException("oder table is saved .oder details table is not saved.");

            }
            return oderRequestDTO.getCustomerName().concat("oder is saved.");

        } else {
            throw new RuntimeException("oder table is not saved .");
        }

    }






    /********
     * //error
     //map struct------->   request yawaddi ewana deta entity ekat hariyat set wenne na.miss match *********************************
     //            1)oder entity eke ( set<oder details> / customer entity /employee entity ) data giye na.error enaw
     //            2)oder details list eke (oder entity / item entity) data giye na . error enaw
     **********/
/*

    @Transactional
    @Override
    public String saveOder(OderRequestDTO oderRequestDTO) {
        //data oder details ekat set karanne na thma (set<OderDetailsEntity>)
        OderEntity oderEntity = oderMapper.oderRequestDTOToOderEntity(oderRequestDTO);
        //entity ekat data kotasak gihin tiyenne. aye set karanna oni na ( customer entity,employee entity) eka entity ekakat
        //save denaw oder entity ekat
        oderRepo.save(oderEntity);

        //oder eka save una kiyal check karal oder details ekat data yawanaw
        if (oderRepo.existsById(oderEntity.getOderId())) {

            List<OderDetailsRequestDTO> oderDetailsRequestDTOList = oderRequestDTO.getOderDetailsRequestDTOList();
            List<OderDetailsEntity> oderDetailsEntityList = oderMapper.oderDetailsRequestDTOListToOderDetailsEntityList(oderDetailsRequestDTOList);
            //entity list ekat kotasak gihin kotasak na(oder entity,item entity) entity list ekakt
            for (int i = 0; i < oderDetailsEntityList.size(); i++) {
                oderDetailsEntityList.get(i).setOderEntity(oderEntity);
                oderDetailsEntityList.get(i).setItemEntity(itemRepo.getById(oderRequestDTO.getOderDetailsRequestDTOList().get(i).getItemEntity()));
            }

            //oder details ekat data giyad kiyal check karanaw
            if (oderDetailsEntityList.size() > 0) {
                //save All denaw oder details ekat
                oderDetailsRepo.saveAll(oderDetailsEntityList);
            } else {
                throw new RuntimeException("oder table is saved .oder details table is not saved.");

            }
            return oderRequestDTO.getCustomerName().concat("oder is saved.");

        } else {
            throw new RuntimeException("oder table is not saved .");
        }

    }


 */

    /*****************************************************************
     //no error
     //for walin request eke daya yanna hariyat constructor hadanna oni
     //            1)oder entity eke ( set<oder details> anin kara  constructor hadanw )
     // (customer entity /employee entity) awan id eken entity gannaw
     //            2)oder details list eke (oder entity ) data giye na . error enaw
     /// (item entity) awan
     *************/
/*
    @Transactional
    @Override
    public String saveOder(OderRequestDTO oderRequestDTO) {

        OderEntity oderEntity = new OderEntity(
                oderRequestDTO.getCustomerName(),
                oderRequestDTO.getEmployeeName(),
                oderRequestDTO.getData(),
                oderRequestDTO.getGrossAmount(),
                oderRequestDTO.getTotalDiscount(),
                oderRequestDTO.getNetAmount(),
                oderRequestDTO.getGiftVoucher(),
                oderRequestDTO.getCash(),
                oderRequestDTO.getTotalCharge(),
                oderRequestDTO.getNoOfItem(),
                customerRepo.getReferenceById(oderRequestDTO.getCustomerEntity()),
                employeeRepo.getReferenceById(oderRequestDTO.getEmployeeEntity())
        );

        //for loop eken karaddi (set<oder details >/ oder id/oder active state) data yawenne na
        //save denaw oder entity ekat
        oderRepo.save(oderEntity);
        //oder eka save una kiyal check karal oder details ekat data yawanaw
        if (oderRepo.existsById(oderEntity.getOderId())) {

            List<OderDetailsRequestDTO> oderDetailsRequestDTOList = oderRequestDTO.getOderDetailsRequestDTOList();
            List<OderDetailsEntity> oderDetailsEntityList = new ArrayList<>();
            for (OderDetailsRequestDTO oderDetailsRequestDTO : oderDetailsRequestDTOList) {

                OderDetailsEntity oderDetailsEntity = new OderDetailsEntity(
                        oderDetailsRequestDTO.getItemName(),
                        oderDetailsRequestDTO.getQty(),
                        oderDetailsRequestDTO.getUnitDiscount(),
                        oderDetailsRequestDTO.getTotal(),
                        oderEntity,
                        itemRepo.getReferenceById(oderDetailsRequestDTO.getItemEntity())

                );
                oderDetailsEntityList.add(oderDetailsEntity);
            }
            if (oderDetailsEntityList.size() > 0) {
                //save All denaw oder details ekat
                oderDetailsRepo.saveAll(oderDetailsEntityList);
            } else {
                throw new RuntimeException("oder table is saved .oder details table is not saved.");
            }
            return oderRequestDTO.getCustomerName().concat("oder is saved.");
        } else {
            throw new RuntimeException("oder table is not saved .");
        }
    }

 */

    @Override
    public OderResponseDTO getOderById(int oderId) {
        if (oderRepo.existsById(oderId)) {

            OderEntity oderEntity = oderRepo.getReferenceById(oderId);
            //model mapper
            OderResponseDTO oderResponseDTO = modelMapper.map(oderEntity, OderResponseDTO.class);


            //map strct
            //        OderResponseDTO oderResponseDTO=oderMapper.oderEntityToOderResponseDTO(oderEntity);

            //for looop
//                 OderResponseDTO oderResponseDTO = new OderResponseDTO(
//                    oderEntity.getOderId(),
//                    oderEntity.getCustomerName(),
//                    oderEntity.getEmployeeName(),
//                    oderEntity.getData(),
//                    oderEntity.getGrossAmount(),
//                    oderEntity.getTotalDiscount(),
//                    oderEntity.getNetAmount(),
//                    oderEntity.getGiftVoucher(),
//                    oderEntity.getCash(),
//                    oderEntity.getTotalCharge(),
//                    oderEntity.getNoOfItem(),
//                    oderEntity.isOderActiveState(),
//                    oderEntity.getCustomerEntity().getCustomerId(), ///////
//                    oderEntity.getEmployeeEntity().getEmployeeId() ///////
//            );


            return oderResponseDTO;
        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public OderPaginationDTO getOderByCustomerId(int customerId, int page) {

        int size = 2;
        boolean activeState = true;
        Page<OderEntity> oderEntityList = oderRepo.findAllByCustomerEntityEqualsAndOderActiveStateEquals(customerRepo.getReferenceById(customerId), PageRequest.of(page, size), activeState);
        long count = oderRepo.countAllByCustomerEntityEqualsAndOderActiveStateEquals(customerRepo.getReferenceById(customerId), activeState);
        if (count > 0) {
            //model mapper
            List<OderEntity> oderEntities = oderEntityList.getContent();
            List<OderResponseDTO> oderResponseDTOList = modelMapper.map(oderEntities, new TypeToken<List<OderResponseDTO>>() {
            }.getType());

            //map strct
            //    List<OderResponseDTO> oderResponseDTOList = oderMapper.oderEntityListToOderResponseDTOList(oderEntityList);

            //for loop
         /*   List<OderResponseDTO> oderResponseDTOList = new ArrayList<>();
            for (OderEntity oderEntity : oderEntityList) {
                OderResponseDTO oderResponseDTO = new OderResponseDTO(
                        oderEntity.getOderId(),
                        oderEntity.getCustomerName(),
                        oderEntity.getEmployeeName(),
                        oderEntity.getData(),
                        oderEntity.getGrossAmount(),
                        oderEntity.getTotalDiscount(),
                        oderEntity.getNetAmount(),
                        oderEntity.getGiftVoucher(),
                        oderEntity.getCash(),
                        oderEntity.getTotalCharge(),
                        oderEntity.getNoOfItem(),
                        oderEntity.isOderActiveState(),
                        oderEntity.getCustomerEntity().getCustomerId(),
                        oderEntity.getEmployeeEntity().getEmployeeId()
                );
                oderResponseDTOList.add(oderResponseDTO);
            }
          */
            return new OderPaginationDTO(
                    oderResponseDTOList, count
            );
        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public String deleteOderById(int oderId) {

        if (oderRepo.existsById(oderId)) {

            OderEntity oderEntity = oderRepo.getReferenceById(oderId);
            List<OderDetailsEntity> oderDetailsEntityList = oderDetailsRepo.findAllByOderEntityEquals(oderEntity);
            if (oderDetailsEntityList.size() > 0) {
                for (int i = 0; i < oderDetailsEntityList.size(); i++) {
                    oderDetailsRepo.delete(oderDetailsEntityList.get(i));
                }
                oderRepo.delete(oderEntity);
                return oderId + " is deleted.";
            } else {
                throw new NotFoundException("no list found");
            }
        } else {
            throw new NotFoundException("no id found");
        }
    }


    @Override
    public CustomerAndOderPaginationDTO getCustomerAndOderByState(boolean activeState, int page, int size) {

        //type method tiyena interface use karanne.
        //response dto eka ba
        List<CustomerAndOderResponseInterface> customerAndOderResponseInterfaceList = oderRepo.getCustomerAndOderDetailsByState(activeState, PageRequest.of(page, size));
        long count = oderRepo.countCustomerAndOderDetailsByState(activeState);

        if (count > 0) {
            //model mapper
            List<CustomerAndOderResponseDTO> customerAndOderResponseDTOList = modelMapper.map(customerAndOderResponseInterfaceList, new TypeToken<List<CustomerAndOderResponseDTO>>() {
            }.getType());

            //for each

//            List<CustomerAndOderResponseDTO> customerAndOderResponseDTOList = new ArrayList<>();
//            for (CustomerAndOderResponseInterface customerAndOderResponseInterface : customerAndOderResponseInterfaceList) {
//                CustomerAndOderResponseDTO customerAndOderResponseDTO = new CustomerAndOderResponseDTO(
//                        customerAndOderResponseInterface.getCustomerId(),
//                        customerAndOderResponseInterface.getCustomerName(),
//                        customerAndOderResponseInterface.getCustomerAddress(),
//                        customerAndOderResponseInterface.getOderId(),
//                        customerAndOderResponseInterface.getEmployeeName(),
//                        customerAndOderResponseInterface.getNetAmount()
//                );
//                customerAndOderResponseDTOList.add(customerAndOderResponseDTO);
//            }


            return new CustomerAndOderPaginationDTO(
                    customerAndOderResponseDTOList, count
            );
        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerAndOderDetailsPaginationDTO getCustomerAndOderDetailsByOderId(int oderId, int page, int size) {
        List<CustomerAndOderDetailsResponseInterface> customerAndOderDetailsResponseInterfaceList = oderRepo.getCustomerAndOderDetailsByOderId(oderId, PageRequest.of(page, size));
        long count = oderRepo.countCustomerAndOderDetailsByOderId(oderId);

        if (count > 0) {
            //model mapper
            List<CustomerAndOderDetailsResponseDTO> customerAndOderDetailsResponseDTOList = modelMapper.map(customerAndOderDetailsResponseInterfaceList, new TypeToken<List<CustomerAndOderDetailsResponseDTO>>() {
            }.getType());

            //map struct


            //for each
          /*  List<CustomerAndOderDetailsResponseDTO> customerAndOderDetailsResponseDTOList = new ArrayList<>();
            for (CustomerAndOderDetailsResponseInterface customerAndOderDetailsResponseInterface : customerAndOderDetailsResponseInterfaceList) {
                 CustomerAndOderDetailsResponseDTO customerAndOderDetailsResponseDTO= new CustomerAndOderDetailsResponseDTO(
                        customerAndOderDetailsResponseInterface.getCustomerId(),
                        customerAndOderDetailsResponseInterface.getCustomerName(),
                        customerAndOderDetailsResponseInterface.getCustomerAddress(),
                        customerAndOderDetailsResponseInterface.getOderDetailsId(),
                        customerAndOderDetailsResponseInterface.getItemName(),
                        customerAndOderDetailsResponseInterface.getTotal()
                );
                customerAndOderDetailsResponseDTOList.add(customerAndOderDetailsResponseDTO);

            }

           */
            return new CustomerAndOderDetailsPaginationDTO(
                    customerAndOderDetailsResponseDTOList, count
            );
        } else {
            throw new NotFoundException("not found");
        }
    }

}

