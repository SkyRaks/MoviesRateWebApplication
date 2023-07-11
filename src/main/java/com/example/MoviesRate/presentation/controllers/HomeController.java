package com.example.MoviesRate.presentation.controllers;

import com.example.MoviesRate.data.FileStorageRepository;
import  com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.exceptions.ImportException;
import com.example.MoviesRate.model.Movie;
import com.example.MoviesRate.service.MovieService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@Log4j2
public class HomeController {
    public static final String DISPO = """
            attachment; filename="%s"
            """;
    private MovieRepository movieRepository;
    private FileStorageRepository fileStorageRepository;
    private MovieService movieService;
    public HomeController(MovieRepository movieRepository, FileStorageRepository fileStorageRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.fileStorageRepository = fileStorageRepository;
        this.movieService = movieService;
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/home/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format(DISPO, resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @GetMapping("/newmovie")
    public String getNewMovie() {
        return "newmovie";
    }

    @PostMapping("/saveFilm")
    public String saveMovie(@Valid Movie movie, Errors errors, @RequestParam MultipartFile posterFile) throws IOException {
        log.info(movie);
        log.info("File name: " + posterFile.getOriginalFilename());
        log.info("File size: " + posterFile.getSize());
        log.info("Errors: " + errors);
        if (!errors.hasErrors()) {
            movieService.save(movie, posterFile.getInputStream());
            return "redirect:home";
        }
        return "newmovie";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws IOException {
        movieService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        Optional<Movie> movie = movieRepository.findById(id);
        model.addAttribute("movie", movie);
        return "updatemovie";
    }

    @PostMapping("/user-update")
    public String updateUser(Movie movie, @RequestParam MultipartFile posterFile) throws IOException {
        movieService.save(movie, posterFile.getInputStream());
        return "redirect:/home";
    }

    @PostMapping("/import")
    public String importCSV(@RequestParam MultipartFile csvFile) {
        try {
            movieService.importCSV(csvFile.getInputStream());
        } catch (IOException e) {
            throw new ImportException(e);
        }
        return "redirect:/home";
    }

    @ModelAttribute("movies")
    public Iterable<Movie> getMovie(@PageableDefault(size = 3) Pageable page) {
        return movieService.findAll(page);
    }

    @ModelAttribute
    public Movie getFilm() {
        return new Movie();
    }
}
