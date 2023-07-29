package com.dummybot.movie.service;

import com.dummybot.movie.repo.Movie;
import com.dummybot.movie.repo.MovieRepository;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieReferenceResolver {
  
  private static final String FEDERATED_KEY_NAME = "id";
  private static final String FEDERATION_TYPE_NAME = "Movie";
  
  private static final String OBJECT_TYPE_NAME_FIELD = "__typename";
  
  private final MovieRepository movieRepository;

  public Movie resolveReference(@NonNull Map<String, Object> reference){
    
    if (FEDERATION_TYPE_NAME.equals(reference.get(OBJECT_TYPE_NAME_FIELD)) && !(reference.get(FEDERATED_KEY_NAME) instanceof String)) {
      final String id = (String) reference.get(FEDERATED_KEY_NAME);
      return movieRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("No movie found with the given id"));
    }
    return null;
  }

}
