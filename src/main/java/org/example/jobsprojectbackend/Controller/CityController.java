package org.example.jobsprojectbackend.Controller;

import org.example.jobsprojectbackend.Service.CityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = "*")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // Получить список всех уникальных городов (из обеих таблиц)
    @GetMapping
    public List<String> getAllCities() {
        return cityService.getAllUniqueCities();
    }
}