package org.example.jobsprojectbackend.Controller;

import org.example.jobsprojectbackend.Entity.Vacancy;
import org.example.jobsprojectbackend.Service.VacancyService;
import org.example.jobsprojectbackend.Service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vacancies")
@CrossOrigin(origins = "*")
public class VacancyController {

    private final VacancyService vacancyService;
    private final CompanyService companyService;

    public VacancyController(VacancyService vacancyService, CompanyService companyService) {
        this.vacancyService = vacancyService;
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Page<Vacancy>> getAllVacancies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(vacancyService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
        return vacancyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Vacancy>> search(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(vacancyService.search(query, page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Vacancy>> filterByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(vacancyService.filterByLocation(location, page, size));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Page<Vacancy>> getVacanciesByCompany(
            @PathVariable Long companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "35") int size
    ) {
        return ResponseEntity.ok(vacancyService.getByCompanyId(companyId, page, size));
    }
}