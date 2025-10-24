package org.example.jobsprojectbackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "Backend is running! API available at /api/vacancies/";
    }
}
