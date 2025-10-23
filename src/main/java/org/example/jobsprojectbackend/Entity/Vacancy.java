package org.example.jobsprojectbackend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String location;
    private String salary;
    private String contacts;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER) // сразу подгружать компанию
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties("vacancies") // чтобы избежать рекурсии при сериализации
    private Company company;
}