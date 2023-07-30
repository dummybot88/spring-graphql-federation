package com.dummybot.talent.repo;


import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Talent {
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String director;
  
  private String producer;
  
  @ElementCollection(targetClass = String.class)
  private List<String> actors;
  
  private Long movieId;

  public Talent(String director, String producer, List<String> actors, Long movieId) {
    this.director = director;
    this.producer = producer;
    this.actors = actors;
    this.movieId = movieId;
  }
}
