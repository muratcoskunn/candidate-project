package com.sahabt.candidatepool.dto.request;

import com.sahabt.candidatepool.entity.Gender;
import com.sahabt.candidatepool.entity.LatestEducationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCandidateRequest {
    private String name;
    private String surname;
    private LocalDate birthday;
    private String phone;
    private Gender gender;
    //@Email
    private String mail;
    private String abilities;
    private LatestEducationStatus latestEducationStatus;
    private String educationInformation;
    private String foreignLanguage;
}
