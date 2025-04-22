package com.springboot.posSystemManagement.dto.pagination;

import com.springboot.posSystemManagement.dto.response.OderDetailsResponseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderDetailsPaginationDTO {
    private List<OderDetailsResponseDTO> oderDetailsResponseDTOList;
    private long count;

}
