package com.losung.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    private Date timestamp;
    private String status;
    private String message;
    private Object data;

    public ContactResponse(Date timestamp,String status,String message){
        this.timestamp=timestamp;
        this.status=status;
        this.message=message;
    }
}
