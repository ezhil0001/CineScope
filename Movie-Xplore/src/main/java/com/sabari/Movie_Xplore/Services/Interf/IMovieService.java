package com.sabari.Movie_Xplore.Services.Interf;

import com.sabari.Movie_Xplore.DTO.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IMovieService {
    Response getAllMovies();
    Response addNewMovie( MultipartFile photo, String title, int year, String movieDescription, String duration, String director, String genre, double imdbRating, int votesCount);
    Response getMovieById(int movieId);
    Response deleteMovieById(int movieId);
    Response updateMovie( int movieId, MultipartFile photo, String title, int year, String movieDescription, String duration, String director, String genre, double imdbRating, int votesCount);
}
