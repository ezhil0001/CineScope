package com.sabari.Movie_Xplore.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MovieDto {
    private int id;

    private String title;
    private String description;
    private int year;
    private String duration;
    private String moviePosterURL;
    private String director;
    private String genre;

    private double imdbRating;  // IMDB rating (e.g., 7.5 out of 10)
    private int votesCount;  // Number of votes from users (e.g., 250 users)

    private List<CastDto> cast;

}
