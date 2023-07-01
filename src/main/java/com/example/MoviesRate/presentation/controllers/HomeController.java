package com.example.MoviesRate.presentation.controllers;

import  com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.model.Movie;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
//@RequestMapping // ("/home")
public class HomeController {
    private MovieRepository movieRepository;
    public HomeController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/newmovie")
    public String getNewMovie() {
        return "newmovie";
    }

    @PostMapping("/saveFilm")
    public String saveMovie(@Valid Movie movie, Errors errors) {
        System.out.println(movie);
        if (!errors.hasErrors()) {
            movieRepository.save(movie);
            return "redirect:home";
        }
        return "newmovie";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        movieRepository.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        Optional<Movie> movie = movieRepository.findById(id);
        model.addAttribute("movie", movie);
        return "updatemovie";
    }

    @PostMapping("/user-update")
    public String updateUser(Movie movie){
        movieRepository.save(movie);
        return "redirect:/home";
    }

    @ModelAttribute("movies")
    public Iterable<Movie> getMovie() {
        return movieRepository.findAll();
    }

    @ModelAttribute
    public Movie getFilm() {
        return new Movie();
    }
}
