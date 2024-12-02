package com.sabari.Movie_Xplore.Services.Imp;

import com.sabari.Movie_Xplore.DTO.MovieDto;
import com.sabari.Movie_Xplore.DTO.Response;
import com.sabari.Movie_Xplore.Entity.Movie;
import com.sabari.Movie_Xplore.Exception.OurException;
import com.sabari.Movie_Xplore.Repo.MovieRepo;
import com.sabari.Movie_Xplore.Services.AWS3Service;
import com.sabari.Movie_Xplore.Services.Interf.IMovieService;
import com.sabari.Movie_Xplore.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private AWS3Service awsS3Service;


    public Response addNewMovie(MultipartFile photo, String title, int year, String movieDescription, String duration, String director, String genre, double imdbRating, int votesCount) {
        Response response = new Response();

        try {
            String imageUrl = awsS3Service.saveImageToS3(photo);
            Movie movie = new Movie();
            movie.setMoviePosterURL(imageUrl);
            movie.setTitle(title);
            movie.setYear(year);
            movie.setDescription(movieDescription);
            movie.setDirector(director);
            movie.setGenre(genre);
            movie.setImdbRating(imdbRating);
            movie.setVotesCount(votesCount);
            movie.setDuration(duration);
            Movie savedMovie = movieRepo.save(movie);
            MovieDto movieDto = Utils.mapMovieEntityToMovieDTO(savedMovie);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setMovieDto(movieDto);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a movie " + e.getMessage());
        }
        return response;
    }


    public Response getAllMovies() {
        Response response = new Response();

        try {
            List<Movie> movieList = movieRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<MovieDto> movieDTOList = Utils.mapMovieListEntityToMovieListDTO(movieList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setMovieDtoListList(movieDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a movie " + e.getMessage());
        }
        return response;
    }

    public Response getMovieById(int movieId) {
        Response response = new Response();

        try {
            Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new OurException("movie Not Found"));
            MovieDto movieDto = Utils.mapMovieEntityToMovieDTO(movie);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setMovieDto(movieDto);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a movie " + e.getMessage());
        }
        return response;
    }

    public Response deleteMovieById(int movieId) {
        Response response = new Response();
        try {
            movieRepo.findById(movieId).orElseThrow(() -> new OurException("Movie Not Found"));
            movieRepo.deleteById(movieId);
            response.setStatusCode(200);
            response.setMessage("successful");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a movie " + e.getMessage());
        }
        return response;
    }

    public Response updateMovie(int movieId, MultipartFile photo, String title, int year, String movieDescription, String duration, String director, String genre, double imdbRating, int votesCount) {
        Response response = new Response();
        try{
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }

            Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new OurException("movie Not Found"));
            if(title != null) movie.setTitle(title);
            if(year != -1) movie.setYear(year);
            if(movieDescription != null) movie.setDescription(movieDescription);
            if(duration != null) movie.setDuration(duration);
            if(director != null) movie.setDirector(director);
            if(genre != null) movie.setGenre(genre);
            if(imdbRating != -1) movie.setImdbRating(imdbRating);
            if(votesCount != -1) movie.setVotesCount(votesCount);

            Movie updatedMovie = movieRepo.save(movie);
            MovieDto movieDto = Utils.mapMovieEntityToMovieDTO(updatedMovie);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setMovieDto(movieDto);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a movie " + e.getMessage());
        }
        return response;
    }
}
