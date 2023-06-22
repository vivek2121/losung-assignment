package com.losung.assignment.repository;

import com.losung.assignment.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactDetails,Integer> {

    List<ContactDetails> findByFirstNameContainingIgnoreCase(String firstName);

    List<ContactDetails> findByLastNameContainingIgnoreCase(String lastName);

    List<ContactDetails> findByEmailContainingIgnoreCase(String email);
}
