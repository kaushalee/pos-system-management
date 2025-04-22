package com.springboot.posSystemManagement.dto.pagination;

import com.springboot.posSystemManagement.dto.response.ItemResponseDTO;

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
