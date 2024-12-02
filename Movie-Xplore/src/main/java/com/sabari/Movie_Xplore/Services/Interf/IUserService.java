package com.sabari.Movie_Xplore.Services.Interf;

import com.sabari.Movie_Xplore.DTO.LogInRequest;
import com.sabari.Movie_Xplore.DTO.Response;
import com.sabari.Movie_Xplore.Entity.Users;

public interface IUserService {

    Response register(Users user);

    Response login(LogInRequest loginRequest);

    Response getAllUsers();

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);
}
