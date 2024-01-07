package com.tbert31.banking.impl;

import com.tbert31.banking.dto.AccountDto;
import com.tbert31.banking.dto.UserDto;
import com.tbert31.banking.exceptions.OperationNonPermittedException;
import com.tbert31.banking.models.Account;
import com.tbert31.banking.models.User;
import com.tbert31.banking.services.AccountService;
import com.tbert31.banking.validators.ObjectsValidator;
import com.tbert31.banking.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
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
        /*if(dto.getId() != null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }*/

        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);

        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();

        if(userHasAlreadyAnAccount){
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }

        // generate random IBAN when creating new account else do not update the IBAN
        if(dto.getId() == null){
            account.setIban(generateRandomIban());
        }

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
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        String iban = Iban.random(CountryCode.FR).toFormattedString();

        boolean ibanExists = repository.findByIban(iban).isPresent();

        if(ibanExists){
            generateRandomIban();
        }

        return iban;
    }
}
