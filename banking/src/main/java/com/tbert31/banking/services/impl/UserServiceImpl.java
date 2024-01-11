package com.tbert31.banking.services.impl;


import com.tbert31.banking.config.JwtUtils;
import com.tbert31.banking.dto.AccountDto;
import com.tbert31.banking.dto.AuthenticationRequest;
import com.tbert31.banking.dto.AuthenticationResponse;
import com.tbert31.banking.dto.UserDto;
import com.tbert31.banking.models.User;
import com.tbert31.banking.repositories.UserRepository;
import com.tbert31.banking.services.AccountService;
import com.tbert31.banking.services.UserService;
import com.tbert31.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll(){
        return repository.findAll()
                .stream()
                //.map( u -> UserDto.fromEntity(u))
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        // create a bank account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);

        user.setActive(true);
        repository.save(user);

        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);

        repository.save(user);

        return user.getId();
    }

    @Override
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = repository.save(user);
        String token = jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails user = repository.findByEmail(request.getEmail()).get();
        final String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
