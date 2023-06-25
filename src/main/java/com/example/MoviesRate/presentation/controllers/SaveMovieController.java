package com.example.MoviesRate.presentation.controllers;

import com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/savemovie")
public class SaveMovieController {

    private MovieRepository movieRepository;

    public SaveMovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String getSaveMovie() {
        return "savemovie";
    }

    @GetMapping("/redirect")
    public String goBack() {
        return "redirect:home";
    }

    @PostMapping
    public String saveMovie(Movie movie) {
        System.out.println(movie);
        movieRepository.save(movie);
        return "home";
    }

    @GetMapping("/home")
    public String goBackPage() {
        return "home";
    }
}
