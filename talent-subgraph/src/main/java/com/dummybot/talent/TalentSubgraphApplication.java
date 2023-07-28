package com.dummybot.talent;

import com.dummybot.talent.repo.Movie;
import com.dummybot.talent.repo.Talent;
import com.dummybot.talent.repo.TalentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TalentSubgraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentSubgraphApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(TalentRepository talentRepository){
		return args -> {
			talentRepository.saveAll(
					buildTalentData()
			);
		};
	}

	private static List<Talent> buildTalentData() {
		List<String> actorsForMovieOne = new ArrayList<>();
		actorsForMovieOne.add("Leonardo DiCaprio");
		actorsForMovieOne.add("Jonah Hill");
		actorsForMovieOne.add("Margot Robbie");
		List<String> actorsForMovieTwo = new ArrayList<>();
		actorsForMovieTwo.add("Cillian Murphy");
		actorsForMovieTwo.add("Emily Blunt");
		actorsForMovieTwo.add("Robert D Jr.");
		return List.of(new Talent("Martin Scorsese", actorsForMovieOne, 11L),
				new Talent("Christopher Nolan", actorsForMovieTwo, 12L));
	}

}
