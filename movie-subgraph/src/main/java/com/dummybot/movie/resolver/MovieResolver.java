package com.dummybot.movie.resolver;

import com.dummybot.movie.repo.Movie;
import com.dummybot.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MovieResolver {

  private final MovieService service;

  @QueryMapping
  public Iterable<Movie> fetchAllMovies(){
    return service.getAllMovies();
  }

}
