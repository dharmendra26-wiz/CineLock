package com.dharmendra.CineLock.catalog.show;

import com.dharmendra.CineLock.catalog.movie.Movie;
import com.dharmendra.CineLock.catalog.venue.Screen;
import com.dharmendra.CineLock.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.core.ObjectReadContext;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="shows")
@Getter
@Setter
@NoArgsConstructor
public class Show extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

}
