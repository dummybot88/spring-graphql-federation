package com.dummybot.movie;

import com.dummybot.movie.repo.Movie;
import com.dummybot.movie.repo.MovieRepository;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MovieSubgraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieSubgraphApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(MovieRepository movieRepository){
		return args -> {
			movieRepository.saveAll(
					buildMoviesData()
			);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(final CorsRegistry registry) {
				registry.addMapping("/graphql/**")
						.allowedOrigins(CorsConfiguration.ALL)
						.allowedHeaders(CorsConfiguration.ALL)
						.allowedMethods(CorsConfiguration.ALL);
			}
		};
	}

	private static List<Movie> buildMoviesData() {
		return List.of(new Movie(11L, "Wolf of Wall Street", 2014, 13L),
				new Movie(12L, "Oppenheimer", 2023, 14L));
	}

}
