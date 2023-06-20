package com.example.MoviesRate.presentation.controllers;

import  com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.model.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private MovieRepository movieRepository;

    public HomeController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ModelAttribute("movies")
    public Iterable<Movie> getMovie() {
        return movieRepository.findAll();
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @ModelAttribute
    public Movie getFilm() {
        return new Movie();
    }

    @GetMapping("/redirect")
    public String saveMovie() {
        return "redirect:savemovie";
    }

    @GetMapping("/savemovie")
    public String savePage() {
        return "savemovie";
    }
}
