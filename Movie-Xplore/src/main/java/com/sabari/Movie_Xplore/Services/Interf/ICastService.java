package com.sabari.Movie_Xplore.Services.Interf;

import com.sabari.Movie_Xplore.DTO.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ICastService {

    Response getAllCast();
    Response getCastById(int id);
    Response addNewCast(MultipartFile file, String name);
    Response updateCast(int id, String name, MultipartFile file);
    Response deleteCast(int id);
}
