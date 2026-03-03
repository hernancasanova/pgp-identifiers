package com.identifiers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private boolean success;
    private String message;
    private Object data;
    
    public ResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}