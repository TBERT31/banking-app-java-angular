package com.tbert31.banking.services;

import com.tbert31.banking.dto.AuthenticationRequest;
import com.tbert31.banking.dto.AuthenticationResponse;
import com.tbert31.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
