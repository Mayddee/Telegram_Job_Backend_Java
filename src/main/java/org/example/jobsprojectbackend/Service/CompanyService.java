package org.example.jobsprojectbackend.Service;

import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repo;

    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public Page<Company> getAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Optional<Company> getById(Long id) {
        return repo.findById(id);
    }

    public Optional<Company> getByName(String name) {
        return repo.findByCompanyNameIgnoreCase(name);
    }

    public Page<Company> search(String query, int page, int size) {
        if (query == null || query.isBlank()) {
            return getAll(page, size);
        }
        return repo.search(query.trim(), PageRequest.of(page, size));
    }

    public Page<Company> filterByCity(String city, int page, int size) {
        if (city == null || city.isBlank()) {
            return getAll(page, size);
        }
        return repo.findByCity(city.trim(), PageRequest.of(page, size));
    }
}
