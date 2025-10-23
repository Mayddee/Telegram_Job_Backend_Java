package org.example.jobsprojectbackend.Service;

import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository repo;

    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public List<Company> getAll() {
        return repo.findAll();
    }

    public Optional<Company> getByName(String name) {
        return repo.findByCompanyNameIgnoreCase(name);
    }

    public Optional<Company> getById(Long id) {
        return repo.findById(id);
    }


    public List<Company> search(String query) {
        if (query == null || query.isBlank()) {
            return getAll();
        }
        return repo.search(query.trim());
    }

    public List<Company> filterByCity(String city) {
        if (city == null || city.isBlank()) {
            return getAll();
        }
        return repo.findByCity(city.trim());
    }
}
