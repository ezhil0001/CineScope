package com.sabari.Movie_Xplore.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CastDto {
    private int id;

    private String name;
    private String imageURL;
    private MovieDto movieDto;
}
