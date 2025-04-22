package com.springboot.posSystemManagement.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StanderdResponse {

    private int code;
    private String message;
    private Object response;

}
