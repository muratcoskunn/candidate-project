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
        PdfExtracter pdf = new PdfExtracter();
        pdf.GetNameFromPdf(text);
        //System.err.println(text);
        int i = 0;
        List fullname = new ArrayList();
        while(text.charAt(i) !=','){
            fullname.add(text.charAt(i));
            i++;
        }
        int counter = 0;
        System.err.println(fullname);
        List<Character> name = new ArrayList<>();
        while(text.charAt(counter)!=' '){
            name.add(text.charAt(counter));
            counter++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c :name
             ) {
            stringBuilder.append(c);
        }
        String nameString = stringBuilder.toString();
        System.err.println(nameString);
        StringBuilder lastnameBuilder = new StringBuilder();
        counter++;
        while(text.charAt(counter)!=' '){
            lastnameBuilder.append(text.charAt(counter));
            counter++;
        }
        String lastname = lastnameBuilder.toString();
        System.err.println(lastname);
        counter = counter+3;
        StringBuilder ageBuilder = new StringBuilder();
        while (text.charAt(counter)!='\r'){
            ageBuilder.append(text.charAt(counter));
            counter++;
        }
        System.err.println(counter);
        System.err.println("yas = "+ageBuilder.toString());
        StringBuilder phoneBuilder = new StringBuilder();
        for (int k =0;k<text.length();k++){
            if(text.charAt(k)=='C'){
                if (text.charAt(k+1)=='e'){
                    if(text.charAt(k+2)=='p'){
                        System.err.println("k="+k);
                        while(text.charAt(k)!='\n'){
                            k++;
                        }
                        System.err.println("k="+k);
                        k++;
                        while(text.charAt(k)!='\r'){
                            phoneBuilder.append(text.charAt(k));
                            k++;

                        }
                    }
                }
            }
        }
        candidate.setPhone(phoneBuilder.toString());
        System.err.println(ageBuilder.toString());
        String age = ageBuilder.toString();
        document.close();
        candidate.setName(nameString);
        candidate.setSurname(lastname);
        candidate.setAge(age);
        candidateRepository.save(candidate);
        return candidate;

    }
}
