package com.losung.assignment.repository;

import com.losung.assignment.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactDetails,Integer> {
}
