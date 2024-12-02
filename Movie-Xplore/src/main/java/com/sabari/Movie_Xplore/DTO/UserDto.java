package com.sabari.Movie_Xplore.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private int id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String role;
}
