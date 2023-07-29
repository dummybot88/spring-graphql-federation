package com.dummybot.movie.service;

import com.dummybot.movie.repo.Movie;
import com.dummybot.movie.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;

  public Iterable<Movie> getAllMovies(){
    return movieRepository.findAll();
  }
  
}
