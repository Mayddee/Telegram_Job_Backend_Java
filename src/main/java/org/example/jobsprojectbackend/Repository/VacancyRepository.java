package org.example.jobsprojectbackend.Repository;

import org.example.jobsprojectbackend.Entity.Company;
import org.example.jobsprojectbackend.Entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByCompany_Id(Long companyId);

    @Query("""
        SELECT v FROM Vacancy v
        WHERE 
            LOWER(v.position) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.location) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.salary) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.description) LIKE LOWER(CONCAT('%', :query, '%'))
         OR LOWER(v.company.companyName) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<Vacancy> searchAll(@Param("query") String query);

    @Query("""
    SELECT v FROM Vacancy v
    WHERE LOWER(v.location) LIKE LOWER(CONCAT('%', :location, '%'))
""")
    List<Vacancy> findByLocation(@Param("location") String location);

    @Query("SELECT DISTINCT v.location FROM Vacancy v WHERE v.location IS NOT NULL AND v.location <> ''")
    List<String> findAllLocations();

    List<Vacancy> findByCompanyId(Long companyId);
}