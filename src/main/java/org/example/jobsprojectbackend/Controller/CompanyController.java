package org.example.jobsprojectbackend.Controller;

import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Company>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String name) {
        return service.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Company>> searchCompanies(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(service.search(query, page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Company>> filterByCity(
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(service.filterByCity(city, page, size));
    }
}
