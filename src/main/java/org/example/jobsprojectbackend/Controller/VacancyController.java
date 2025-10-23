package org.example.jobsprojectbackend.Controller;
import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Entity.Vacancy;
import org.example.jobsprojectbackend.Service.CompanyService;
import org.example.jobsprojectbackend.Service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Vacancy> getAllVacancies() {
        return vacancyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
        return vacancyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Vacancy> search(@RequestParam(required = false) String query) {
        return vacancyService.search(query);
    }

    @GetMapping("/filter")
    public List<Vacancy> filterByLocation(@RequestParam String location) {
        return vacancyService.filterByLocation(location);
    }

    @GetMapping("/company/{companyId}")
    public List<Vacancy> getVacanciesByCompany(@PathVariable Long companyId) {
        return vacancyService.getByCompanyId(companyId);
    }


}