package com.sahabt.candidatepool.dto.response;

import com.sahabt.candidatepool.entity.Gender;
import com.sahabt.candidatepool.entity.LatestEducationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCandidateResponse {
    private long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String phone;
    private Gender gender;
    private String mail;
    private String abilities;
    private LatestEducationStatus latestEducationStatus;
    private String educationInformation;
    private String foreignLanguage;
    private Byte[] curriculumVitae;
}
