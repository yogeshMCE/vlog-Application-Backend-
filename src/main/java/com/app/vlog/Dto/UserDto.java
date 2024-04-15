package com.app.vlog.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=3,message = "Name should be minimum of 3 characters")
    private String name;
    @Email(message ="Email is not valid ")
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message = "About should be minimum of 3 Length and maximum of 10 length")
    private String about;

    @NotEmpty
    private String password;
}
