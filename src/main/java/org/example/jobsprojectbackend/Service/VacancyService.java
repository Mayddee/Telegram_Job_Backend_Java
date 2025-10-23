package org.example.jobsprojectbackend.Service;
import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Entity.Vacancy;
import org.example.jobsprojectbackend.Repository.CompanyRepository;
import org.example.jobsprojectbackend.Repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VacancyService {
    private final VacancyRepository vacancyRepo;
    private final CompanyRepository companyRepo;

    public VacancyService(VacancyRepository vacancyRepo, CompanyRepository companyRepo) {
        this.vacancyRepo = vacancyRepo;
        this.companyRepo = companyRepo;
    }

    public List<Vacancy> getAll() {
        return vacancyRepo.findAll();
    }

    public List<Vacancy> getByCompanyId(Long id) {
        return vacancyRepo.findByCompany_Id(id);
    }

    public Optional<Vacancy> getById(Long id) {
        return vacancyRepo.findById(id);
    }

    public List<Vacancy> search(String query) {
        if (query == null || query.isBlank()) {
            return getAll();
        }
        return vacancyRepo.searchAll(query.trim());
    }

    public List<Vacancy> filterByLocation(String location) {
        if (location == null || location.isBlank()) {
            return getAll();
        }
        return vacancyRepo.findByLocation(location.trim());
    }


}