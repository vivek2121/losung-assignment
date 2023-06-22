package com.losung.assignment.service;

import com.losung.assignment.constant.MessageConstant;
import com.losung.assignment.dto.request.ContactRequest;
import com.losung.assignment.dto.response.ContactResponse;
import com.losung.assignment.model.ContactDetails;
import com.losung.assignment.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ResponseEntity<ContactResponse> saveContactDetails(ContactRequest contactRequest) {
        ContactDetails contactDetails = ContactDetails.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .email(contactRequest.getEmail())
                .phoneNumber(contactRequest.getPhoneNumber())
                .build();
        return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200, MessageConstant.CONTACT_SAVED,
                contactRepository.save(contactDetails)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContactResponse> getContactDetails(Integer contactId) {
        Optional<ContactDetails> contactDetails = contactRepository.findById(contactId);
        if (contactDetails.isPresent()) {

            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_FETCHED,contactDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                    MessageConstant.NO_DATA_FOUND,new ContactDetails()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ContactResponse> deleteContactDetails(Integer contactId) {
        Optional<ContactDetails> contactDetails = contactRepository.findById(contactId);
        if (contactDetails.isPresent()) {
            contactRepository.deleteById(contactId);
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_DELETED), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(),
                    MessageConstant.STATUS_404, MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ContactResponse> updateContactDetails(Integer contactId, ContactRequest contactRequest) {
        Optional<ContactDetails> contactDetails = contactRepository.findById(contactId);
        if (contactDetails.isPresent()) {
            contactDetails = Optional.ofNullable(contactDetails.get().toBuilder()
                    .firstName(contactRequest.getFirstName())
                    .lastName(contactRequest.getLastName())
                    .email(contactRequest.getEmail())
                    .phoneNumber(contactRequest.getPhoneNumber())
                    .build());
            contactRepository.save(contactDetails.get());
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_UPDATED,contactDetails.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContactResponse> getAllContactDetails() {
        List<ContactDetails> contactDetails = contactRepository.findAll();
        if (!contactDetails.isEmpty()) {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_LIST,contactDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                    MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<ContactResponse> getAllContactDetailsByFirstName(String firstName) {
        List<ContactDetails> contactDetails = contactRepository.findByFirstNameContainingIgnoreCase(firstName);
        if (!contactDetails.isEmpty()) {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_LIST,contactDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                    MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ContactResponse> getAllContactDetailsByLastName(String lastName) {
        List<ContactDetails> contactDetails = contactRepository.findByLastNameContainingIgnoreCase(lastName);
        if (!contactDetails.isEmpty()) {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200,
                    MessageConstant.CONTACT_LIST,contactDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                    MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ContactResponse> getAllContactDetailsByEmail(String email) {
        List<ContactDetails> contactDetails = contactRepository.findByEmailContainingIgnoreCase(email);
        if (!contactDetails.isEmpty()) {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_200, MessageConstant.CONTACT_LIST,
                    contactDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactResponse(new Date(), MessageConstant.STATUS_404,
                    MessageConstant.NO_DATA_FOUND), HttpStatus.OK);
        }
    }
}
