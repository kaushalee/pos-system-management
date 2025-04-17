package com.springboot.customerAndOrderManagementApplication.dto.pagination;

import com.springboot.customerAndOrderManagementApplication.dto.response.ItemResponseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPaginationDTO {

    private List<ItemResponseDTO> itemResponseDTOList;
    private long count;
}
