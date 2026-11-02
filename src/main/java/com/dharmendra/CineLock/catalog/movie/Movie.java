package com.dharmendra.CineLock.catalog.movie;

import com.dharmendra.CineLock.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="movies")
@Getter
@Setter
@NoArgsConstructor
public class Movie extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="duration_mins",nullable = false)
    private Integer durationMins;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String genre;

    @Column(name="release_date")
    private LocalDate releaseDate;
}
