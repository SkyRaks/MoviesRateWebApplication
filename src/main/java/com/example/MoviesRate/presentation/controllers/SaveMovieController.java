package com.example.MoviesRate.presentation.controllers;

import com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.model.Movie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@Controller
@RequestMapping("/savemovie")
public class SaveMovieController {

    private MovieRepository movieRepository;

    public SaveMovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ModelAttribute
    public Movie getFilm() {
        return new Movie();
    }

//    @GetMapping("/savemovie")
//    public String getSaveMovie() {
//        return "savemovie";
//    }

//    @PostMapping
//    public String saveMovie(@Valid Movie movie, Errors errors) {
//        System.out.println(movie);
//        if (!errors.hasErrors()) {
//            movieRepository.save(movie);
//            return "redirect:home";
//        }
//        return "savemovie";
//    }

//    @GetMapping(name = "edit")
//    public String editMovie(@RequestParam Long edit, Model model) {
//        System.out.println(edit);
//        Optional<Movie> movie = movieRepository.findById(edit);
//        model.addAttribute("movie", movie);
//        return "/update";
//    }

//    @PostMapping("/update")
//    public String update(Movie movie) {
//        System.out.println(movie);
//        movieRepository.save(movie);
//        return "redirect:home";
//    }
}
