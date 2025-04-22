package com.springboot.posSystemManagement.controller;

import com.springboot.posSystemManagement.dto.pagination.CustomerPaginationDTO;
import com.springboot.posSystemManagement.dto.request.CustomerRequestDTO;
import com.springboot.posSystemManagement.dto.response.CustomerResponseDTO;
import com.springboot.posSystemManagement.dto.update.CustomerUpdateDTO;
import com.springboot.posSystemManagement.service.CustomerService;
import com.springboot.posSystemManagement.util.StanderdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//post-200,204
//put-201,204
//get,delete-201
// error -404(not found)
//200-created
//201-ok
//204-no contex
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/saveCustomer")
    public ResponseEntity<StanderdResponse> saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        String text = customerService.saveCustomer(customerRequestDTO);

      /*
      return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(200, "success", text),
                HttpStatus.CREATED
        );
       */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new StanderdResponse(200, "success", text));
    }


    @PutMapping(path = "/updateCustomer")
    public ResponseEntity<StanderdResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {

        String text = customerService.updateCustomer(customerUpdateDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/deleteCustomer", params = "id")
    public ResponseEntity<StanderdResponse> deleteCustomer(@RequestParam(value = "id") int customerId) {

        String text = customerService.deleteCustomer(customerId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getCustomer", params = "id")
    public ResponseEntity<StanderdResponse> getCustomer(@RequestParam(value = "id") int customerId) {

        CustomerResponseDTO customerResponseDTO = customerService.getCustomer(customerId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerResponseDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getCustomerByCity", params = {"city", "page", "size"})
    public ResponseEntity<StanderdResponse> getCustomerByCity(@RequestParam(value = "city") String customerAddress,
                                                              @RequestParam(value = "page") int page,
                                                              @RequestParam(value = "size") int size) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getCustomerByCity(customerAddress, page, size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getCustomerBySalary", params = {"salary", "page"})
    public ResponseEntity<StanderdResponse> getCustomerBySalary(@RequestParam(value = "salary") double customerSalary,
                                                                @RequestParam(value = "page") int page
    ) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getCustomerBySalary(customerSalary, page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAllCustomerStateIsTrue", params = "page")
    public ResponseEntity<StanderdResponse> getAllCustomerStateIsTrue(@RequestParam(value = "page") int page) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getAllCustomerStateIsTrue(page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAll", params = "page")
    public ResponseEntity<StanderdResponse> getAll(@RequestParam(value = "page") int page) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getAll(page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getAllCustomer", params = {"page", "size"})
    public ResponseEntity<StanderdResponse> getAllCustomer(@RequestParam(value = "page") int page,
                                                           @RequestParam(value = "size") int size) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getAllCustomer(page, size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }



    @GetMapping(path = "/getAllCustomerWithState", params = {"state", "page"})
    public ResponseEntity<StanderdResponse> getAllCustomerWithState(@RequestParam(value = "state") boolean activeState,
                                                                    @RequestParam(value = "page") int page) {

        CustomerPaginationDTO customerPaginationDTO = customerService.getAllCustomerWithState(activeState, page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", customerPaginationDTO),
                HttpStatus.OK
        );
    }


}
