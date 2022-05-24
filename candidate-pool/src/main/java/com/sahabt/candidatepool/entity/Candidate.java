package com.sahabt.candidatepool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String age;
    private LocalDate birthday;
    private String phone;
    private Gender gender;
    //@Email
    private String mail;
    private String abilities;
    private LatestEducationStatus latestEducationStatus;
    private String educationInformation;
    private String foreignLanguage;
    @Lob
    @Column(columnDefinition = "longblob")
    @Basic(fetch = FetchType.LAZY)
    private byte[] curriculumVitae;

    public Candidate(byte[] curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }
}
