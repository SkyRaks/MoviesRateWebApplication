package com.example.MoviesRate.service;

import com.example.MoviesRate.data.FileStorageRepository;
import com.example.MoviesRate.data.MovieRepository;
import com.example.MoviesRate.exceptions.StorageException;
import com.example.MoviesRate.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.zip.ZipInputStream;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final FileStorageRepository fileStorageRepository;

    public MovieService(MovieRepository movieRepository, FileStorageRepository fileStorageRepository) {
        this.movieRepository = movieRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    public Movie save(Movie movie, InputStream inputStream) {
        Movie savedMovie = movieRepository.save(movie);
        fileStorageRepository.save(movie.getPosterFile(), inputStream);
        return savedMovie;
    }

    public void deleteById(Long id) throws IOException {
        Optional<Movie> movie = movieRepository.findById(id);
        Optional<String> posterName = movie.map(Movie::getPosterFile);
        fileStorageRepository.deleteByName(posterName);
        movieRepository.deleteById(id);
    }

    public Optional<Movie> findById(Long aLong) {
        return movieRepository.findById(aLong);
    }

    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void importCSV(InputStream csvStream) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(csvStream);
            zipInputStream.getNextEntry();
            InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines()
                    .skip(1)
                    .map(Movie::parse)
                    .forEach(movieRepository::save);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }
}
