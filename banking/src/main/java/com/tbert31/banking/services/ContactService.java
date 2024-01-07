package com.tbert31.banking.services;

import com.tbert31.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);
}
