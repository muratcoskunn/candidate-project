package com.sahabt.candidatepool.service.business;

import org.springframework.web.multipart.MultipartFile;

public interface PdfExtracter {
    String GetNameFromPdf(String text);
    String GetSurnameFromPdf(String text);
    String GetAgeFromPdf(String text);
    String GetPhoneFromPdf(String text);
}
