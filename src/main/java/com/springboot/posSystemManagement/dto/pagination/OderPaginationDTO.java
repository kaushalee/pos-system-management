package com.springboot.posSystemManagement.dto.pagination;

import com.springboot.posSystemManagement.dto.response.OderResponseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderPaginationDTO {

    private List<OderResponseDTO> oderResponseDTOList;
    private long count;
}
