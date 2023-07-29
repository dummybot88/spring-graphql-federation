package com.dummybot.talent;

import static com.dummybot.talent.service.TalentReferenceResolver.OBJECT_TYPE_NAME_FIELD;
import static com.dummybot.talent.service.TalentReferenceResolver.TYPE_NAME_MOVIE;
import static com.dummybot.talent.service.TalentReferenceResolver.TYPE_NAME_TALENT;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.dummybot.talent.repo.Movie;
import com.dummybot.talent.service.TalentReferenceResolver;
import graphql.schema.DataFetcher;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;

@Configuration
public class GraphQlConfig {
  @Bean
  public GraphQlSourceBuilderCustomizer federationTransform(TalentReferenceResolver talentReferenceResolver) {
    return builder ->
        builder.schemaFactory((registry, wiring)->
            Federation.transform(registry, wiring)
                .fetchEntities(getDataFetcher(talentReferenceResolver))
                .resolveEntityType(new ClassNameTypeResolver())
                .build()
        );
  }

  private static DataFetcher<?> getDataFetcher(
      TalentReferenceResolver talentReferenceResolver) {
    return env -> {
      List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
      return representations.stream()
          .map(representation -> {
            final String typeName = String.valueOf(representation.get(OBJECT_TYPE_NAME_FIELD));
            return getTypeObject(talentReferenceResolver, representation, typeName);
          })
          .collect(Collectors.toList());
    };
  }

  private static Object getTypeObject(TalentReferenceResolver talentReferenceResolver,
      Map<String, Object> representation, String typeName) {
    if (TYPE_NAME_MOVIE.equals(typeName)) {
      return Movie.resolveReference(representation);
    }
    return 
        (TYPE_NAME_TALENT.equals(typeName)) ?
            talentReferenceResolver.resolveReference(representation) : null;
  }
}
