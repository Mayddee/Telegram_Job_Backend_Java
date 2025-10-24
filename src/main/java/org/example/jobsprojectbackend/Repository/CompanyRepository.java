package org.example.jobsprojectbackend.Repository;

import org.example.jobsprojectbackend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyNameIgnoreCase(String name);

    @Query("""
        SELECT c FROM Company c
        WHERE LOWER(c.companyName) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(c.companyDescription) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(c.city) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    Page<Company> search(@Param("query" ) String query, Pageable pageable);

    // Фильтр по городу
    @Query("""
        SELECT c FROM Company c
        WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))
    """)
    Page<Company> findByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT DISTINCT c.city FROM Company c WHERE c.city IS NOT NULL AND c.city <> ''")
    List<String> findAllCities();
}