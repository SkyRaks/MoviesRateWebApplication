package com.example.MoviesRate.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorimport")
public class ImportErrorController {

    @GetMapping
    public String getErrorPage() {
        return "errorimport";
    }
}
