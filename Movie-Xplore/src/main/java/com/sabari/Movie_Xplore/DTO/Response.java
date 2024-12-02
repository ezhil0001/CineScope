package com.sabari.Movie_Xplore.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;

    private UserDto user;
    private MovieDto movieDto;
    private CastDto castDto;
    private List<UserDto> userList;
    private List<MovieDto> movieDtoListList;
    private List<CastDto> castDtoList;


}
