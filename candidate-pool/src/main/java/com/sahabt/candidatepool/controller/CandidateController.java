package com.sahabt.candidatepool.controller;

import com.sahabt.candidatepool.dto.request.AddCandidateRequest;
import com.sahabt.candidatepool.dto.response.AddCandidateResponse;
import com.sahabt.candidatepool.dto.response.GetCandidateResponse;
import com.sahabt.candidatepool.dto.response.RemoveCandidateResponse;
import com.sahabt.candidatepool.entity.Candidate;
import com.sahabt.candidatepool.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestScope
@RequestMapping("/candidate")
@CrossOrigin
@AllArgsConstructor
public class CandidateController {
    private CandidateService candidateService;
    @GetMapping
    public List<GetCandidateResponse> getAllCandidates(){
        return candidateService.getAllCandidates();
    }
    @PostMapping
    public AddCandidateResponse addCandidate(@RequestBody @Valid AddCandidateRequest addCandidateRequest ){
        return candidateService.addCandidate(addCandidateRequest);
    }
    @DeleteMapping
    public RemoveCandidateResponse removeCandidate(@RequestParam long candidateId){
        return candidateService.removeCandidate(candidateId);
    }
    @RequestMapping(value = "/getPdf",method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Candidate addCv(@RequestParam long id ,@RequestPart("file") MultipartFile multipartFile) throws IOException {
        return candidateService.addCv(id,multipartFile);
    }

}
