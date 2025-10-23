package org.example.jobsprojectbackend.Service;

import org.example.jobsprojectbackend.Repository.CompanyRepository;
import org.example.jobsprojectbackend.Repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityService {

    private final CompanyRepository companyRepository;
    private final VacancyRepository vacancyRepository;

    public CityService(CompanyRepository companyRepository, VacancyRepository vacancyRepository) {
        this.companyRepository = companyRepository;
        this.vacancyRepository = vacancyRepository;
    }

    public List<String> getAllUniqueCities() {
        // Получаем города из двух таблиц
        List<String> companyCities = companyRepository.findAllCities();
        List<String> vacancyLocations = vacancyRepository.findAllLocations();

        // Объединяем и убираем дубликаты
        return Stream.concat(companyCities.stream(), vacancyLocations.stream())
                .filter(city -> city != null && !city.isBlank())
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}