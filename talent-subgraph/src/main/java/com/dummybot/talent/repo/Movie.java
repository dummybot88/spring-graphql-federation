package com.dummybot.talent.repo;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie {
  
  @Id
  private Long id;
  
  public static Movie resolveReference(@NonNull Map<String, Object> reference){
    return Movie.builder().id(Long.parseLong(String.valueOf(reference.get("id")))).build();
  }

}
