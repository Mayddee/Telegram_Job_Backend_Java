package org.example.jobsprojectbackend.Repository;

import org.example.jobsprojectbackend.Entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Page<Vacancy> findByCompany_Id(Long companyId, Pageable pageable);

    @Query("""
        SELECT v FROM Vacancy v
        WHERE 
            LOWER(v.position) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.location) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.salary) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.description) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.company.companyName) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    Page<Vacancy> searchAll(@Param("query") String query, Pageable pageable);

    @Query("""
        SELECT v FROM Vacancy v
        WHERE LOWER(v.location) LIKE LOWER(CONCAT('%', :location, '%'))
    """)
    Page<Vacancy> findByLocation(@Param("location") String location, Pageable pageable);

    @Query("SELECT DISTINCT v.location FROM Vacancy v WHERE v.location IS NOT NULL AND v.location <> ''")
    List<String> findAllLocations();
}
