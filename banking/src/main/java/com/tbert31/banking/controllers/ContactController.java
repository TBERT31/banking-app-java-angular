package com.tbert31.banking.controllers;

import com.tbert31.banking.dto.ContactDto;
import com.tbert31.banking.services.AddressService;
import com.tbert31.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody ContactDto contactDto
    ){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(
            @PathVariable("contact-id") Integer contactId
    ){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("contact-id") Integer contactId
    ){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
