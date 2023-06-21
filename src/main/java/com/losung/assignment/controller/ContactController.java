package com.losung.assignment.controller;

import com.losung.assignment.dto.request.ContactRequest;
import com.losung.assignment.dto.response.ContactResponse;
import com.losung.assignment.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@Api(tags = "Contact API")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    @ApiOperation("create contact details")
    public ResponseEntity<ContactResponse> saveContactDetails(
            @RequestBody ContactRequest contactRequest) {
        return contactService.saveContactDetails(contactRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation("fetch contact details")
    public ResponseEntity<ContactResponse> getContactDetails(@PathVariable Integer id) {
        return contactService.getContactDetails(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete contact details")
    public ResponseEntity<ContactResponse> deleteContactDetails(@PathVariable Integer id) {
        return contactService.deleteContactDetails(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("update contact details")
    public ResponseEntity<ContactResponse> updateContactDetails(@PathVariable Integer id,@RequestBody ContactRequest contactRequest) {
        return contactService.updateContactDetails(id,contactRequest);
    }

    @GetMapping
    @ApiOperation("get list of contact's")
    public ResponseEntity<ContactResponse> getAllContactDetails() {
        return contactService.getAllContactDetails();
    }

}
