package com.dharmendra.CineLock.catalog.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateMovieRequest(
        @NotBlank(message = "Movie title is required")
        @Size(max =255,message = "Tittle cannot exceed 255 characters")
        String title,
        String description,
        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be greater than 0 minutes")
        Integer durationMins,
        @NotBlank(message = "Language is required")
        String language,
        @NotBlank(message = "Genre is required")
        String genre,
        LocalDate releaseDate
        ){

}
