package com.sabari.Movie_Xplore.util;

import com.sabari.Movie_Xplore.DTO.CastDto;
import com.sabari.Movie_Xplore.DTO.MovieDto;
import com.sabari.Movie_Xplore.DTO.UserDto;
import com.sabari.Movie_Xplore.Entity.Cast;
import com.sabari.Movie_Xplore.Entity.Movie;
import com.sabari.Movie_Xplore.Entity.Users;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }


    public static UserDto mapUserEntityToUserDTO(Users user) {
        UserDto userDTO = new UserDto();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static MovieDto mapMovieEntityToMovieDTO(Movie movie) {
        MovieDto movieDTO = new MovieDto();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setYear(movie.getYear());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setMoviePosterURL(movie.getMoviePosterURL());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setImdbRating(movie.getImdbRating());
        movieDTO.setVotesCount(movie.getVotesCount());

        return movieDTO;
    }

    public static CastDto mapCastEntityToCastDto(Cast cast) {
        CastDto castDTO = new CastDto();
        castDTO.setId(cast.getId());
        castDTO.setName(cast.getName());
        castDTO.setImageURL(cast.getImageURL());
        return castDTO;
    }

    public static List<UserDto> mapUserListEntityToUserListDTO(List<Users> userList) {
        return userList.stream()
                .map(Utils::mapUserEntityToUserDTO)
                .collect(Collectors.toList());
    }

    public static List<MovieDto> mapMovieListEntityToMovieListDTO(List<Movie> moviesList) {
        return moviesList.stream()
                .map(Utils::mapMovieEntityToMovieDTO)
                .collect(Collectors.toList());
    }

    public static List<CastDto> mapCastListEntityToCastListDTO(List<Cast> castList) {
        return castList.stream()
                .map(Utils::mapCastEntityToCastDto)
                .collect(Collectors.toList());
    }

}
