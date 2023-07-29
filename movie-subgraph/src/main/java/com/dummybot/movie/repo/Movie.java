package com.dummybot.movie.repo;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  @Id
  private Long id;
  
  private String title;
  
  private int releaseYear;
  
  private Long talentId;
}
