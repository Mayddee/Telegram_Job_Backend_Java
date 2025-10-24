package org.example.jobsprojectbackend.Service;

import org.example.jobsprojectbackend.Entity.Vacancy;
import org.example.jobsprojectbackend.Repository.VacancyRepository;
import org.example.jobsprojectbackend.Repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepo;
    private final CompanyRepository companyRepo;

    public VacancyService(VacancyRepository vacancyRepo, CompanyRepository companyRepo) {
        this.vacancyRepo = vacancyRepo;
        this.companyRepo = companyRepo;
    }

    // Получить все вакансии с пагинацией
    public Page<Vacancy> getAll(int page, int size) {
        return vacancyRepo.findAll(PageRequest.of(page, size));
    }

    // Получить вакансии конкретной компании
    public Page<Vacancy> getByCompanyId(Long id, int page, int size) {
        return vacancyRepo.findByCompany_Id(id, PageRequest.of(page, size));
    }

    // Получить вакансию по ID
    public Optional<Vacancy> getById(Long id) {
        return vacancyRepo.findById(id);
    }

    // Поиск по запросу с пагинацией
    public Page<Vacancy> search(String query, int page, int size) {
        if (query == null || query.isBlank()) {
            return getAll(page, size);
        }
        return vacancyRepo.searchAll(query.trim(), PageRequest.of(page, size));
    }

    // Фильтр по локации с пагинацией
    public Page<Vacancy> filterByLocation(String location, int page, int size) {
        if (location == null || location.isBlank()) {
            return getAll(page, size);
        }
        return vacancyRepo.findByLocation(location.trim(), PageRequest.of(page, size));
    }
}