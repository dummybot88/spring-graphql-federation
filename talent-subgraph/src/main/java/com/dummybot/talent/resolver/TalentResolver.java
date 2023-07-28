package com.dummybot.talent.resolver;

import com.dummybot.talent.input.MovieInput;
import com.dummybot.talent.repo.Movie;
import com.dummybot.talent.repo.Talent;
import com.dummybot.talent.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TalentResolver {
  
  private final TalentService talentService;
  
  @SchemaMapping
  public Talent talent(Movie movie){
    return talentService.fetchTalentByMovie(movie);
  }
  
  @QueryMapping
  public Talent talentDetails(@Argument MovieInput movieInput){
    return talentService.fetchTalentByMovie(new Movie(movieInput.getId()));
  }
  
  @QueryMapping
  public Iterable<Talent> allDetails(){
    return talentService.fetchTalentDetails();
  }

}
