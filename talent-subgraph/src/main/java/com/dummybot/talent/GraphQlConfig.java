package com.dummybot.talent;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.dummybot.talent.repo.Movie;
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
  public GraphQlSourceBuilderCustomizer federationTransform() {
    DataFetcher<?> entityDataFetcher = env -> {
      List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
      System.out.println("ArgumentName:" + representations);
      return representations.stream()
          .map(representation -> {
            if ("Movie".equals(representation.get("__typename"))) {
              return new Movie(Long.parseLong((String) representation.get("id")));
            }
            return null;
          })
          .collect(Collectors.toList());
    };

    return builder ->
        builder.schemaFactory((registry, wiring)->
            Federation.transform(registry, wiring)
                .fetchEntities(entityDataFetcher)
                .resolveEntityType(new ClassNameTypeResolver())
                .build()
        );
  }
}
