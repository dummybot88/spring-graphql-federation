package com.dummybot.movie;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.dummybot.movie.service.MovieReferenceResolver;
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
  public GraphQlSourceBuilderCustomizer federationTransform(MovieReferenceResolver movieReferenceResolver){
    return builder -> builder.schemaFactory(((typeDefinitionRegistry, runtimeWiring) ->
        Federation.transform(typeDefinitionRegistry, runtimeWiring)
            .fetchEntities(getDataFetcher(movieReferenceResolver))
            .setFederation2(true)
            .resolveEntityType(new ClassNameTypeResolver())
            .build()
    ));
  }

  private static DataFetcher<?> getDataFetcher(MovieReferenceResolver movieReferenceResolver) {
    return env -> {
      List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
      return representations.stream()
          .map(representation -> movieReferenceResolver.resolveReference(representation))
          .collect(Collectors.toList());
    };
  }

}
