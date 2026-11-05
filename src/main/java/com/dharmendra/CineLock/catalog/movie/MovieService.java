package com.dharmendra.CineLock.catalog.movie;

import com.dharmendra.CineLock.catalog.movie.dto.CreateMovieRequest;
import com.dharmendra.CineLock.catalog.movie.dto.MovieResponse;
import com.dharmendra.CineLock.common.exception.DuplicateResourceException;
import com.dharmendra.CineLock.common.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;
    @Transactional
    public MovieResponse createMovie(CreateMovieRequest request){
        if (movieRepository.existsByTitleIgnoreCase(request.title())){
            throw new DuplicateResourceException(
                    "Movie with tittle"+request.title()+"already exists"
            );
        }
        Movie movie =new Movie();
        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setLanguage(request.language());
        movie.setGenre(request.genre());
        movie.setDurationMins(request.durationMins());
        movie.setReleaseDate(request.releaseDate());

        Movie savedMovie =movieRepository.save(movie);
        return MovieResponse.fromEntity(savedMovie);
    }
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id:" + id));
        return MovieResponse.fromEntity(movie);
    }
    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll().stream().map(MovieResponse::fromEntity).toList();
    }
    @Transactional
    public void deleteMovie(Long id){
        if (!movieRepository.existsById(id)){
            throw new ResourceNotFoundException("Movie not found with id:"+ id );
        }
        movieRepository.deleteById(id);
    }
}
