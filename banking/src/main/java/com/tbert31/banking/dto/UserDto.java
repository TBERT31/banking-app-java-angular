package com.tbert31.banking.dto;

import com.tbert31.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull(message = "Le prénom ne doit pas être vide")
    @NotEmpty(message = "Le prénom ne doit pas être vide")
    @NotBlank(message = "Le prénom ne doit pas être vide")
    private String firstname;

    @NotNull(message = "Le nom ne doit pas être vide")
    @NotEmpty(message = "Le nom ne doit pas être vide")
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String lastname;

    @NotNull(message = "L'email ne doit pas être vide")
    @NotEmpty(message = "L'email ne doit pas être vide")
    @NotBlank(message = "L'email ne doit pas être vide")
    @Email(message = "L'email n'est pas conforme")
    private String email;

    @NotNull(message = "Le mot de passe ne doit pas être vide")
    @NotEmpty(message = "Le mot de passe ne doit pas être vide")
    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    @Size(min = 8, max=50, message = "Le mot de passe doit-être compris entre 8 et 5 caractères")
    private String password;

    private String iban;

    private boolean active;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .iban(user.getAccount() == null ? "" : user.getAccount().getIban())
                .active(user.isActive())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
