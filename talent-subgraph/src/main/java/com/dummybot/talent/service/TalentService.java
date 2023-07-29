package com.dummybot.talent.service;

import com.dummybot.talent.repo.Movie;
import com.dummybot.talent.repo.Talent;
import com.dummybot.talent.repo.TalentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TalentService {
  
  private final TalentRepository talentRepository;
  
  public Talent fetchTalentByMovie(Movie movie){
    return talentRepository.findByMovieId(movie.getId());
  }
  
  public Talent fetchTalentById(Long id){
    return talentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No talent team found"));
  }
  
  public Iterable<Talent> fetchTalentDetails(){
    return talentRepository.findAll();
  }

}
