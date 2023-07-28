package com.dummybot.talent.repo;

import org.springframework.data.repository.CrudRepository;

public interface TalentRepository extends CrudRepository<Talent, Long> {
  
  Talent findByMovieId(Long id);
  
  Talent findByDirector(String directorName);

}
