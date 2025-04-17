package com.springboot.customerAndOrderManagementApplication.service.serviceIMPL;

import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.CustomerRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.CustomerResponseDTO;
import com.springboot.customerAndOrderManagementApplication.dto.update.CustomerUpdateDTO;
import com.springboot.customerAndOrderManagementApplication.entity.CustomerEntity;
import com.springboot.customerAndOrderManagementApplication.exception.NotFoundException;
import com.springboot.customerAndOrderManagementApplication.repo.CustomerRepo;
import com.springboot.customerAndOrderManagementApplication.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public String saveCustomer(CustomerRequestDTO customerRequestDTO) {

        //all args constructor

//        CustomerEntity customerEntity=new CustomerEntity(
//                1,
//                customerRequestDTO.getCustomerName(),
//                customerRequestDTO.getCustomerAddress(),
//                customerRequestDTO.getCustomerNIC(),
//                customerRequestDTO.getCustomerSalary(),
//                false,
//                null
//        );

        //customer entity eke constructor

//        CustomerEntity customerEntity=new CustomerEntity(
//
//                customerRequestDTO.getCustomerName(),
//                customerRequestDTO.getCustomerAddress(),
//                customerRequestDTO.getCustomerNIC(),
//                customerRequestDTO.getCustomerSalary()
//
//        );


        CustomerEntity customerEntity = modelMapper.map(customerRequestDTO, CustomerEntity.class);
        customerRepo.save(customerEntity);
        return customerRequestDTO.getCustomerName().concat("is saved.");
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {

        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
            CustomerEntity customerEntity = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customerEntity.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customerEntity.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customerEntity);
            return customerUpdateDTO.getCustomerId() + " is updated";
        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {

        if (customerRepo.existsById(customerId)) {

            CustomerEntity customerEntity = customerRepo.getReferenceById(customerId);
            customerRepo.delete(customerEntity);
            return customerId + " is deleted.";
        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerResponseDTO getCustomer(int customerId) {

        if (customerRepo.existsById(customerId)) {

            CustomerEntity customerEntity = customerRepo.getReferenceById(customerId);

//             CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(
//                     customerEntity.getCustomerId(),
//                     customerEntity.getCustomerName(),
//                     customerEntity.getCustomerAddress(),
//                     customerEntity.getCustomerNIC(),
//                     customerEntity.getCustomerSalary(),
//                     customerEntity.isCustomerActiveState()
//             );


            CustomerResponseDTO customerResponseDTO = modelMapper.map(customerEntity, CustomerResponseDTO.class);
            return customerResponseDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerPaginationDTO getCustomerByCity(String customerAddress, int page, int size) {
        boolean activeState = true;
        Page<CustomerEntity> customerEntityPage = customerRepo.findAllByCustomerAddressContainsAndCustomerActiveStateEquals(customerAddress, PageRequest.of(page, size), activeState);
        long count = customerRepo.countAllByCustomerAddressContainsAndCustomerActiveStateEquals(customerAddress, activeState);
        if (count > 0) {    //customerEntityPage.size()>0

//            List<CustomerResponseDTO> customerResponseDTOList=new ArrayList<>();
//            for (CustomerEntity customerEntity:customerEntityPage){
//
//                CustomerResponseDTO customerResponseDTO=new CustomerResponseDTO(
//
//                        customerEntity.getCustomerId(),
//                        customerEntity.getCustomerName(),
//                        customerEntity.getCustomerAddress(),
//                        customerEntity.getCustomerNIC(),
//                        customerEntity.getCustomerSalary(),
//                        customerEntity.isCustomerActiveState()
//                );
//                customerResponseDTOList.add( customerResponseDTO);
//            }

            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityPage, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType());

            /***********/
            //map struct waladi aulak na.
            //ath meke page ekath ekka aulk anawa
            List<CustomerEntity> customerEntities = customerEntityPage.getContent();
            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
            }.getType());
            CustomerPaginationDTO customerPaginationDTO = new CustomerPaginationDTO(
                    customerResponseDTOList, count
            );
            return customerPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerPaginationDTO getCustomerBySalary(double customerSalary, int page) {

        boolean activeState = true;
        int size = 3;
        Page<CustomerEntity> customerEntityList = customerRepo.findAllByCustomerSalaryGreaterThanEqualAndCustomerActiveStateEquals(customerSalary, PageRequest.of(page, size), activeState);
        long count = customerRepo.countAllByCustomerSalaryGreaterThanEqualAndCustomerActiveStateEquals(customerSalary, activeState);
        if (count > 0) {//customerEntityList.SIze()>0

//            List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
//            for (CustomerEntity customerEntity : customerEntityList) {
//
//                CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(
//
//                        customerEntity.getCustomerId(),
//                        customerEntity.getCustomerName(),
//                        customerEntity.getCustomerAddress(),
//                        customerEntity.getCustomerNIC(),
//                        customerEntity.getCustomerSalary(),
//                        customerEntity.isCustomerActiveState()
//                );
//                customerResponseDTOList.add(customerResponseDTO);
//            }

            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityList, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType())//
            /***/
            List<CustomerEntity> customerEntities = customerEntityList.getContent();
     List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
            }.getType());
            CustomerPaginationDTO customerPaginationDTO = new CustomerPaginationDTO(
                    customerResponseDTOList, count
            );
            return customerPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerPaginationDTO getAllCustomerStateIsTrue(int page) {
        boolean activeState = true;
        int size = 3;
        Page<CustomerEntity> customerEntityList = customerRepo.findAllByCustomerActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = customerRepo.countAllByCustomerActiveStateEquals(activeState);
        if (count > 0) {//customerEntityList.SIze()>0

            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityList, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType());
            /***/
            List<CustomerEntity> customerEntities = customerEntityList.getContent();
            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
            }.getType());
            CustomerPaginationDTO customerPaginationDTO = new CustomerPaginationDTO(
                    customerResponseDTOList, count
            );
            return customerPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerPaginationDTO getAll(int page) {
        int size = 3;
        //error
        Page<CustomerEntity> customerEntityList = customerRepo.findAll(PageRequest.of(page, size));
        long count=customerRepo.count();
        if (count>0){
            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityList, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType());
            /***/
            List<CustomerEntity> customerEntities = customerEntityList.getContent();
           List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
            }.getType());

           CustomerPaginationDTO customerPaginationDTO=new CustomerPaginationDTO(
                   customerResponseDTOList,count
           );
            return customerPaginationDTO;
        }else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public CustomerPaginationDTO getAllCustomer(int page, int size) {


        Page<CustomerEntity> customerEntityList = customerRepo.findAll(PageRequest.of(page, size));
        long count = customerRepo.count();
        if (count > 0) {//customerEntityList.SIze()>0

            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityList, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType());
            /***/
            List<CustomerEntity> customerEntities = customerEntityList.getContent();
            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
            }.getType());
            CustomerPaginationDTO customerPaginationDTO = new CustomerPaginationDTO(
                    customerResponseDTOList, count
            );
            return customerPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public CustomerPaginationDTO getAllCustomerWithState(boolean activeState, int page) {

        int size = 3;
        Page<CustomerEntity> customerEntityList = customerRepo.findAllByCustomerActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = customerRepo.countAllByCustomerActiveStateEquals(activeState);
        if (count > 0) {//customerEntityList.SIze()>0

            /***********/
            List<CustomerEntity> customerEntities = customerEntityList.getContent();
            //error
//            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntityList, new TypeToken<List<CustomerResponseDTO>>() {
//            }.getType());
            List<CustomerResponseDTO> customerResponseDTOList = modelMapper.map(customerEntities, new TypeToken<List<CustomerResponseDTO>>() {
                       }.getType());
            CustomerPaginationDTO customerPaginationDTO = new CustomerPaginationDTO(
                    customerResponseDTOList, count
            );
            return customerPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


}
