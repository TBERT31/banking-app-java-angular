package com.tbert31.banking.impl;

import com.tbert31.banking.dto.AccountDto;
import com.tbert31.banking.dto.UserDto;
import com.tbert31.banking.models.Account;
import com.tbert31.banking.models.User;
import com.tbert31.banking.services.AccountService;
import com.tbert31.banking.validators.ObjectsValidator;
import com.tbert31.banking.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        // to do generate random IBAN
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        //todo generate an IBAN


        //Check if the IBAN already exists

        //if exists -> generate new random IBAN

        //if not exist -> return generated IBAN

        return "";
    }
}
