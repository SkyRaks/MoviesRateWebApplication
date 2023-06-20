package com.example.MoviesRate.data;

import com.example.MoviesRate.model.Movie;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class MovieDataLoader implements ApplicationRunner {
    private MovieRepository movieRepository;

    public MovieDataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (movieRepository.count() == 0) {
            List<Movie> movies = List.of(
                    new Movie(null, "John Wick", "Chad Stahelski", LocalDate.of(2014, 10, 24), new BigDecimal(20000000), 8F),
                    new Movie(null, "Openheimer", "hristopher Nolan", LocalDate.of(2023, 7, 20), new BigDecimal(100000000), 9F),
                    new Movie(null, "Barbie", "Greta Gerwig", LocalDate.of(2023, 7, 19), new BigDecimal(60000000), 10F)
            );
            movieRepository.saveAll(movies);
        }
    }
}
