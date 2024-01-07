package com.tbert31.banking.impl;


import com.tbert31.banking.dto.UserDto;
import com.tbert31.banking.models.User;
import com.tbert31.banking.repositories.UserRepository;
import com.tbert31.banking.services.UserService;
import com.tbert31.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        // User savedUser = repository.save(user);
        // return savedUser.getId();
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
}
