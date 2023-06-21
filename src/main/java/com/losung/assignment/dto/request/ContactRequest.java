package com.losung.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
