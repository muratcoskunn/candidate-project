package com.sahabt.candidatepool.service.business;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KariyerExtracter implements PdfExtracter{
    @Override
    public String GetNameFromPdf(String text) {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while(text.charAt(counter)!=' '){
            stringBuilder.append(text.charAt(counter));
            counter++;
        }
        return stringBuilder.toString();
    }

    @Override
    public String GetSurnameFromPdf(String text) {
        int counter = 0;
        StringBuilder lastNameBuilder = new StringBuilder();
        while(text.charAt(counter)!= ' '){
            counter ++;
        }
        counter++;
        while(text.charAt(counter)!= ' '){
            lastNameBuilder.append(text.charAt(counter));
            counter++;
        }
        return lastNameBuilder.toString();
    }

    @Override
    public String GetAgeFromPdf(String text) {
        int counter = 0;
        StringBuilder ageBuilder = new StringBuilder();
        while(text.charAt(counter)!= ' '){
            counter ++;
        }
        counter++;
        while(text.charAt(counter)!= ' '){
            counter++;
        }
        counter = counter+3;

        while (text.charAt(counter)!='\r'){
            ageBuilder.append(text.charAt(counter));
            counter++;
        }
        return ageBuilder.toString();
    }

    @Override
    public String GetPhoneFromPdf(String text) {
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
        return phoneBuilder.toString();
    }
}
