package com.springboot.posSystemManagement.service;

import com.springboot.posSystemManagement.dto.pagination.ItemPaginationDTO;
import com.springboot.posSystemManagement.dto.request.ItemRequestDTO;
import com.springboot.posSystemManagement.dto.response.ItemResponseDTO;
import com.springboot.posSystemManagement.dto.update.ItemUpdateDTO;

public interface ItemService {
    String saveItem(ItemRequestDTO itemRequestDTO);

    String updateItem(ItemUpdateDTO itemUpdateDTO);

    String deleteItem(int itemId);

    ItemResponseDTO getItem(int itemId);


    ItemPaginationDTO getItemLessThanDiscountPercentage(double itemDiscountPercentage, int page);

    ItemPaginationDTO getAllItemStateIsTrue(int page);

    ItemPaginationDTO getAllItem(int page, int size);

    ItemPaginationDTO getAllItemWithState(boolean activeState, int page);

    ItemPaginationDTO getItemByNameContains(String itemName, int page, int size);
}
