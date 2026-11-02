package com.dharmendra.CineLock.catalog.movie.dto;

import com.dharmendra.CineLock.catalog.movie.Movie;

import java.time.Instant;
import java.time.LocalDate;

public record MovieResponse(
        Long id,
        String title,
        String description,
        Integer durationMins,
        String language,
        String genre,
        LocalDate releaseDate,
        Instant createdAt
) {
    public static MovieResponse fromEntity (Movie movie){
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDurationMins(),
                movie.getLanguage(),
                movie.getGenre(),
                movie.getReleaseDate(),
                movie.getCreatedAt()
        );
    }
}
