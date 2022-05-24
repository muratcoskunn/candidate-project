package com.sahabt.candidatepool.service;

import com.sahabt.candidatepool.dto.request.AddCandidateRequest;
import com.sahabt.candidatepool.dto.response.AddCandidateResponse;
import com.sahabt.candidatepool.dto.response.GetCandidateResponse;
import com.sahabt.candidatepool.dto.response.RemoveCandidateResponse;
import com.sahabt.candidatepool.entity.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidateService {
    List<GetCandidateResponse> getAllCandidates();

    AddCandidateResponse addCandidate(AddCandidateRequest addCandidateRequest);

    RemoveCandidateResponse removeCandidate(long candidateId);

    Candidate addCv(long id, MultipartFile multipartFile) throws IOException;
}
