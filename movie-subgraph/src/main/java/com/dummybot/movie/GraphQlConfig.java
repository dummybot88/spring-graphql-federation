package com.dummybot.movie;

import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQlConfig {

  @Bean
  public GraphQlSourceBuilderCustomizer transform(){
    return builder -> {
      builder.schemaFactory(((typeDefinitionRegistry, runtimeWiring) ->
          Federation.transform(typeDefinitionRegistry, runtimeWiring)
              .fetchEntities(env -> null)
              .setFederation2(true)
              .resolveEntityType(env -> null)
              .build()
      ));
    };
  }

}
