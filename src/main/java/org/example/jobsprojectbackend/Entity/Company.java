package org.example.jobsprojectbackend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description", columnDefinition = "TEXT")
    private String companyDescription;

    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("company")
    private List<Vacancy> vacancies;

}