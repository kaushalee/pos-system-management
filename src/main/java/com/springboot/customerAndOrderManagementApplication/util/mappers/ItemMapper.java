package com.springboot.customerAndOrderManagementApplication.util.mappers;

import com.springboot.customerAndOrderManagementApplication.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")

public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    default ItemEntity fromId(int id) {
        if (id == 0) {
            return null;
        }
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(id); // Set the primary key
        return itemEntity;
        }
}
