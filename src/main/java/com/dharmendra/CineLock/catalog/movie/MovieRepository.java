package com.dharmendra.CineLock.catalog.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    boolean existsByTitleIgnoreCase(String title);

    Optional<Movie> findByTitleIgnoreCase(String title);
}
