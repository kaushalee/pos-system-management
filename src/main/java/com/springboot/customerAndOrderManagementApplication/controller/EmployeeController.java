package com.springboot.customerAndOrderManagementApplication.controller;


import com.springboot.customerAndOrderManagementApplication.dto.pagination.EmployeePaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.EmployeeRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.EmployeeResponseDTO;
import com.springboot.customerAndOrderManagementApplication.dto.update.EmployeeUpdateDTO;
import com.springboot.customerAndOrderManagementApplication.service.EmployeeService;
import com.springboot.customerAndOrderManagementApplication.util.StanderdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

//security wala ResponseEntity<StanderdResponse> wage  ResponseEntity<String> yawanne
    @PostMapping(path = "/saveEmployee")
    public ResponseEntity<StanderdResponse> saveEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        String text = employeeService.saveEmployee(employeeRequestDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(200, "success", text),
                HttpStatus.CREATED
        );

    }


    @PutMapping(path = "/updateEmployee")
    public ResponseEntity<StanderdResponse> updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDTO) {

        String text = employeeService.updateEmployee(employeeUpdateDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/deleteEmployee", params = "id")
    public ResponseEntity<StanderdResponse> deleteEmployee(@RequestParam(value = "id") int employeeId) {

        String text = employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getEmployee", params = "id")
    public ResponseEntity<StanderdResponse> getEmployee(@RequestParam(value = "id") int employeeId) {

        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployee(employeeId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeeResponseDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getAllEmployeeStateIsTrue", params = "page")
    public ResponseEntity<StanderdResponse> getAllEmployeeStateIsTrue(@RequestParam(value = "page") int page) {

        EmployeePaginationDTO employeePaginationDTO = employeeService.getAllEmployeeStateIsTrue(page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeePaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAllEmployee", params = {"page", "size"})
    public ResponseEntity<StanderdResponse> getAllEmployee(@RequestParam(value = "page") int page,
                                                           @RequestParam(value = "size") /*@Max(50) */int size) {

        EmployeePaginationDTO employeePaginationDTO= employeeService.getAllEmployee(page,size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeePaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAllEmployeeWithState", params = {"state", "page"})
    public ResponseEntity<StanderdResponse> getAllEmployeeWithState(@RequestParam(value = "state") boolean activeState,
                                                                    @RequestParam(value = "page") int page) {

        EmployeePaginationDTO employeePaginationDTO= employeeService.getAllEmployeeWithState(activeState,page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeePaginationDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getEmployeeByCity/{city}/{page}/{size}"/*, params = {"city", "page", "size"}*/)
    public ResponseEntity<StanderdResponse> getEmployeeByCity(@PathVariable(value = "city") String employeeAddress,
                                                              @PathVariable(value = "page") int page,
                                                              @PathVariable(value = "size") int size) {

        EmployeePaginationDTO employeePaginationDTO = employeeService.getEmployeeByCity(employeeAddress, page, size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeePaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getEmployeeGreaterThanSalary", params = {"salary", "page"})
    public ResponseEntity<StanderdResponse> getEmployeeGreaterThanSalary(@RequestParam(value = "salary") double employeeSalary,
                                                                         @RequestParam(value = "page") int page
    ) {

        EmployeePaginationDTO employeePaginationDTO = employeeService.getEmployeeGreaterThanSalary(employeeSalary, page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", employeePaginationDTO),
                HttpStatus.OK
        );
    }

}
