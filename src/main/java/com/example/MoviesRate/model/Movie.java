package com.example.MoviesRate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "There can't be a film without a title.")
    private String title;

    @NotEmpty(message = "There can't be a film without a director.")
    private String director;

    @NotNull(message = "There should be at least an approximate release date.")
    private LocalDate releaseDate;

    @DecimalMin(value = "10000.00")
    @NotNull(message = "The budget has to be at least $10000.00")
    private BigDecimal budget;
    private Float score;
}
