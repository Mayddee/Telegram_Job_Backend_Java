package org.example.jobsprojectbackend.Controller;

import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return service.getAll();
    }

    @GetMapping("/{name}")
    public Company getCompanyByName(@PathVariable String name) {
        return service.getByName(name).orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Универсальный поиск
    @GetMapping("/search")
    public List<Company> searchCompanies(@RequestParam(required = false) String query) {
        return service.search(query);
    }

    // Фильтр по городу
    @GetMapping("/filter")
    public List<Company> filterByCity(@RequestParam String city) {
        return service.filterByCity(city);
    }
}
