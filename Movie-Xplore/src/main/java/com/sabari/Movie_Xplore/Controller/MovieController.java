package com.sabari.Movie_Xplore.Controller;


import com.sabari.Movie_Xplore.DTO.Response;
import com.sabari.Movie_Xplore.Services.Interf.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewMovie(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year", required = false) int year,
            @RequestParam(value = "movieDescription", required = false) String movieDescription,
            @RequestParam(value = "duration", required = false) String duration,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "imdbRating", required = false) double imdbRating,
            @RequestParam(value = "votesCount", required = false) int votesCount
    ) {
        if (photo == null || photo.isEmpty()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = movieService.addNewMovie(photo, title, year, movieDescription, duration, director, genre, imdbRating, votesCount);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/movie")
    public ResponseEntity<Response> getMovies() {
        Response response = movieService.getAllMovies();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/movie-by-id/{movieId}")

    public ResponseEntity<Response> getMovieById(@PathVariable int movieId) {
        Response response = movieService.getMovieById(movieId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{movieId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateMovies(
            @PathVariable int movieId,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year", required = false) int year,
            @RequestParam(value = "movieDescription", required = false) String movieDescription,
            @RequestParam(value = "duration", required = false) String duration,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "imdbRating", required = false) double imdbRating,
            @RequestParam(value = "votesCount", required = false) int votesCount
    ) {
        Response response = movieService.updateMovie(movieId, photo, title, year, movieDescription, duration, director, genre, imdbRating, votesCount);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @DeleteMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteMovieById(@PathVariable int movieId) {
        Response response = movieService.deleteMovieById(movieId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
