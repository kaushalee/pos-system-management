package com.springboot.posSystemManagement.config;

import com.springboot.posSystemManagement.dto.request.OderRequestDTO;
import com.springboot.posSystemManagement.dto.response.OderResponseDTO;
import com.springboot.posSystemManagement.entity.OderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //request to entity
        //customer id to customer  entity
        //employee entity to employed id
        //methanadi skip karanawa service ekedi set karanwa model mapper gaththat passe ..///////////////////////////
        //meka nathuwath aulak na //////////////////////
        //list eka thula item ekat ehema /////////////////////////
        // Example of custom mapping
       modelMapper.createTypeMap(OderRequestDTO.class, OderEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(OderEntity::setCustomerEntity); // Skip setting customerEntity
                    mapper.skip(OderEntity::setEmployeeEntity); // Skip setting employeeEntity
                });



        //entity to response
        //customer entity to customer  id
        //employee entity to employed id
        // Custom mapping for OderEntity to OderResponseDTO
        //methanadi skip wenmak na. kelinma model mapper set karanwa witharai.
        //aye passe set krann oni na//////////////////////////////////////////////////////
        modelMapper.addMappings(new PropertyMap<OderEntity, OderResponseDTO>() {
            @Override
            protected void configure() {
                map().setCustomerEntity(source.getCustomerEntity().getCustomerId());
                map().setEmployeeEntity(source.getEmployeeEntity().getEmployeeId());

            }
        }
        );

        return modelMapper;
    }
}

