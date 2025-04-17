package com.springboot.customerAndOrderManagementApplication.util.mappers;

import com.springboot.customerAndOrderManagementApplication.dto.request.OderDetailsRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.request.OderRequestDTO;
import com.springboot.customerAndOrderManagementApplication.dto.response.OderResponseDTO;
import com.springboot.customerAndOrderManagementApplication.entity.ItemEntity;
import com.springboot.customerAndOrderManagementApplication.entity.OderDetailsEntity;
import com.springboot.customerAndOrderManagementApplication.entity.OderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")

public interface OderMapper {

  OderMapper INSTANCE = Mappers.getMapper(OderMapper.class);

  //mapper walt aulak na .. samanay widihat puluwan

  //dto ekak entity ekakt
  // customer entity oni tiyenne id ekak
  // employee entity oni tiyenne id ekak
  //wena wenam set karanna oni na../////////////////////////////
//miss match
  @Mapping(source = "customerEntity", target = "customerEntity.customerId")
  @Mapping(source = "employeeEntity", target = "employeeEntity.employeeId")
  OderEntity oderRequestDTOToOderEntity(OderRequestDTO oderRequestDTO);

  //list ekak convert
  //item entity ona ekat awal tiyenne id ekak
  //wena wenam set karann aoni////////////////////////////////////
  @Mapping(source = "itemEntity", target = "itemEntity", qualifiedByName = "intToItemEntity")
  OderDetailsEntity oderDetailsRequestDTOToOderDetailsEntity(OderDetailsRequestDTO oderDetailsRequestDTO);

  List<OderDetailsEntity> oderDetailsRequestDTOListToOderDetailsEntityList(List<OderDetailsRequestDTO> oderDetailsRequestDTOList);

  @Named("intToItemEntity")
  default ItemEntity intToItemEntity(int itemId) {
    return ItemMapper.INSTANCE.fromId(itemId);
  }




  //entity to dto
  //wena wenam set karanna oni na../////////////////////////////
  @Mapping(source = "customerEntity.customerId", target = "customerEntity")
  @Mapping(source = "employeeEntity.employeeId", target = "employeeEntity")
  OderResponseDTO oderEntityToOderResponseDTO(OderEntity oderEntity);

  List<OderResponseDTO> oderEntityListToOderResponseDTOList(Page<OderEntity> oderEntityList);
}
