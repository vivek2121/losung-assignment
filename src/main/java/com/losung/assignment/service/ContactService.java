package com.losung.assignment.service;

import com.losung.assignment.dto.request.ContactRequest;
import com.losung.assignment.dto.response.ContactResponse;
import org.springframework.http.ResponseEntity;

public interface ContactService {

    ResponseEntity<ContactResponse>saveContactDetails(ContactRequest contactRequest);
    ResponseEntity<ContactResponse>getContactDetails(Integer contactId);
    ResponseEntity<ContactResponse>deleteContactDetails(Integer contactId);
    ResponseEntity<ContactResponse>updateContactDetails(Integer contactId,ContactRequest contactRequest);
    ResponseEntity<ContactResponse>getAllContactDetails();
    ResponseEntity<ContactResponse>getAllContactDetailsByFirstName(String firstName);
    ResponseEntity<ContactResponse>getAllContactDetailsByLastName(String lastName);
    ResponseEntity<ContactResponse>getAllContactDetailsByEmail(String email);

}
