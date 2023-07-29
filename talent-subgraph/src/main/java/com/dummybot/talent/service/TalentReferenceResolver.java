package com.dummybot.talent.service;

import com.dummybot.talent.repo.Talent;
import com.dummybot.talent.repo.TalentRepository;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TalentReferenceResolver {
  
  private final TalentRepository talentRepository;

  private static final String FEDERATED_KEY_NAME = "id";

  public static final String TYPE_NAME_MOVIE = "Movie";
  public static final String TYPE_NAME_TALENT = "Talent";

  public static final String OBJECT_TYPE_NAME_FIELD = "__typename";

  public Talent resolveReference(@NonNull Map<String, Object> reference){

    if (TYPE_NAME_TALENT.equals(reference.get(OBJECT_TYPE_NAME_FIELD)) && !(reference.get(FEDERATED_KEY_NAME) instanceof String)) {
      final String id = (String) reference.get(FEDERATED_KEY_NAME);
      return talentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("No talent team found with the given id"));
    }
    return null;
  }
}
