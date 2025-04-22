package com.springboot.posSystemManagement.controller;

import com.springboot.posSystemManagement.dto.pagination.ItemPaginationDTO;
import com.springboot.posSystemManagement.dto.request.ItemRequestDTO;
import com.springboot.posSystemManagement.dto.response.ItemResponseDTO;
import com.springboot.posSystemManagement.dto.update.ItemUpdateDTO;
import com.springboot.posSystemManagement.service.ItemService;
import com.springboot.posSystemManagement.util.StanderdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/item")

public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping(path = "/saveItem")
    public ResponseEntity<StanderdResponse> saveItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        String text = itemService.saveItem(itemRequestDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(200, "success", text),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/updateItem")
    public ResponseEntity<StanderdResponse> updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO) {

        String text = itemService.updateItem(itemUpdateDTO);

        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/deleteItem", params = "id")
    public ResponseEntity<StanderdResponse> deleteItem(@RequestParam(value = "id") int itemId) {

        String text = itemService.deleteItem(itemId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", text),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getItem", params = "id")
    public ResponseEntity<StanderdResponse> getItem(@RequestParam(value = "id") int itemId) {

        ItemResponseDTO itemResponseDTO = itemService.getItem(itemId);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemResponseDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getItemByNameContains", params = {"name", "page", "size"})
    public ResponseEntity<StanderdResponse> getItemByNameContains(@RequestParam(value = "name") String itemName,
                                                                  @RequestParam(value = "page") int page,
                                                                  @RequestParam(value = "size") int size) {

        ItemPaginationDTO itemPaginationDTO = itemService.getItemByNameContains(itemName, page, size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemPaginationDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getItemLessThanDiscountPercentage", params = {"discountPercentage", "page"})
    public ResponseEntity<StanderdResponse> getItemLessThanDiscountPercentage(@RequestParam(value = "discountPercentage") double itemDiscountPercentage,
                                                                              @RequestParam(value = "page") int page
    ) {

        ItemPaginationDTO itemPaginationDTO = itemService.getItemLessThanDiscountPercentage(itemDiscountPercentage, page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemPaginationDTO),
                HttpStatus.OK
        );
    }


    @GetMapping(path = "/getAllItemStateIsTrue", params = "page")
    public ResponseEntity<StanderdResponse> getAllItemStateIsTrue(@RequestParam(value = "page") int page) {

        ItemPaginationDTO itemPaginationDTO = itemService.getAllItemStateIsTrue(page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemPaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAllItem", params = {"page", "size"})
    public ResponseEntity<StanderdResponse> getAllItem(@RequestParam(value = "page") int page,
                                                       @RequestParam(value = "size") /*@Max(50) */int size) {

        ItemPaginationDTO itemPaginationDTO = itemService.getAllItem(page, size);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemPaginationDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getAllItemWithState", params = {"state", "page"})
    public ResponseEntity<StanderdResponse> getAllItemWithState(@RequestParam(value = "state") boolean activeState,
                                                                @RequestParam(value = "page") int page) {

        ItemPaginationDTO itemPaginationDTO = itemService.getAllItemWithState(activeState, page);
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(201, "success", itemPaginationDTO),
                HttpStatus.OK
        );
    }
}
