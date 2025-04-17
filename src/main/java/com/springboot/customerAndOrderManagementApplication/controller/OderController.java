package com.springboot.customerAndOrderManagementApplication.controller;


import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerAndOderDetailsPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.pagination.CustomerAndOderPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.pagination.OderPaginationDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.OderRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.OderResponseDTO;
import com.springboot.customerAndOrderManagementApplication.service.OderService;
import com.springboot.customerAndOrderManagementApplication.util.StanderdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/oder")
public class OderController {

    @Autowired
    private OderService oderService;

    @PostMapping(path = "/saveOder")
    public ResponseEntity<StanderdResponse> saveOder(@RequestBody OderRequestDTO oderRequestDTO) {
        String text = oderService.saveOder(oderRequestDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(200, "success", text),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/getOderById", params = "id")
    public ResponseEntity<StanderdResponse> getOderById(@RequestParam(value = "id") int oderId) {

        OderResponseDTO oderResponseDTO = oderService.getOderById(oderId);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", oderResponseDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getOderByCustomerId", params = {"id", "page"})
    public ResponseEntity<StanderdResponse> getOderByCustomerId(@RequestParam(value = "id") int customerId,
                                                            @RequestParam(value = "page") int page) {

        OderPaginationDTO oderPaginationDTO = oderService.getOderByCustomerId(customerId, page);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", oderPaginationDTO),
                HttpStatus.OK
        );
    }



    @DeleteMapping(path = "/deleteOderById", params = "id")
    public ResponseEntity<StanderdResponse> deleteOderById(@RequestParam(value = "id") int oderId ){

        String text = oderService.deleteOderById(oderId);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getCustomerAndOderDetailsByState", params = {"state", "page", "size"})
    public ResponseEntity<StanderdResponse> getCustomerAndOderByState(@RequestParam(value = "state") String stateType,
                                                                      @RequestParam(value = "page") int page,
                                                                      @RequestParam(value = "size") int size) {

        //postman thula true ,false denna epa active,in active denna..
       CustomerAndOderPaginationDTO customerAndOderPaginationDTO = null;

        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")) { //active and inactive nam witharai
            boolean activeState = stateType.equalsIgnoreCase("active") ? true : false; //turning opareter
             customerAndOderPaginationDTO  = oderService.getCustomerAndOderByState(activeState, page, size);
        }

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(200, "success", customerAndOderPaginationDTO),
                HttpStatus.CREATED
        );
    }


@GetMapping(path = "/getCustomerAndOderDetailsByOderId",params = {"id","page","size"})
public ResponseEntity<StanderdResponse> getCustomerAndOderDetailsByOderId(
                                                        @RequestParam(value = "id") int oderId,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size){

    CustomerAndOderDetailsPaginationDTO customerAndOderDetailsPaginationDTO =oderService.getCustomerAndOderDetailsByOderId(oderId,page,size);

    return new ResponseEntity<StanderdResponse>(
            new StanderdResponse(201, "success", customerAndOderDetailsPaginationDTO),
            HttpStatus.OK
    );
}

}
