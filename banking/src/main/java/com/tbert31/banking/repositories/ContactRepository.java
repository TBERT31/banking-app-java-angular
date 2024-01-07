package com.tbert31.banking.repositories;

import com.tbert31.banking.models.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Integer> {

  List<Contact> findAllByUserId(Integer userId);
}
