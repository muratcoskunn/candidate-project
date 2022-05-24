package com.sahabt.candidatepool.service.business;

import com.sahabt.candidatepool.dto.request.AddCandidateRequest;
import com.sahabt.candidatepool.dto.response.AddCandidateResponse;
import com.sahabt.candidatepool.dto.response.GetCandidateResponse;
import com.sahabt.candidatepool.dto.response.RemoveCandidateResponse;
import com.sahabt.candidatepool.entity.Candidate;
import com.sahabt.candidatepool.repository.CandidateRepository;
import com.sahabt.candidatepool.service.CandidateService;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private CandidateRepository candidateRepository;
    private ModelMapper modelMapper;
    private PdfExtracter pdfExtracter;
    @Override
    public List<GetCandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream().map(candidate -> modelMapper.map(candidate,GetCandidateResponse.class)).toList();
    }

    @Override
    public AddCandidateResponse addCandidate(AddCandidateRequest addCandidateRequest) {
        var candidate = modelMapper.map(addCandidateRequest, Candidate.class);
        var savedCandidate = candidateRepository.save(candidate);
        var response = modelMapper.map(savedCandidate,AddCandidateResponse.class);
        return response;
    }

    @Override
    public RemoveCandidateResponse removeCandidate(long candidateId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() ->new IllegalArgumentException("Candidate couldn't find"));
        candidateRepository.delete(candidate);
        return new RemoveCandidateResponse();
    }

    @Override
    @Transactional
    public Candidate addCv(long id, MultipartFile multipartFile) throws IOException {
        var candidate = candidateRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Candidate couldn't find"));
        candidate.setCurriculumVitae(multipartFile.getBytes());
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+"fileName");
        multipartFile.transferTo(convFile);
        PDDocument document = PDDocument.load(convFile);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        var name = pdfExtracter.GetNameFromPdf(text);
        var surname = pdfExtracter.GetSurnameFromPdf(text);
        var age = pdfExtracter.GetAgeFromPdf(text);
        var phone = pdfExtracter.GetPhoneFromPdf(text);
        document.close();
        candidate.setName(name);
        candidate.setSurname(surname);
        candidate.setAge(age);
        candidate.setPhone(phone);
        candidateRepository.save(candidate);
        return candidate;

    }
}
