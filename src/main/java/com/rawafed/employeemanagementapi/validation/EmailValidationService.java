package com.rawafed.employeemanagementapi.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailValidationService {

    @Value("${email.validation.api.url}")
    private String emailValidationApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isValidEmail(String email) {
        // Call external email validation API
        String url = emailValidationApiUrl + "?email=" + email;
        Boolean isValid = restTemplate.getForObject(url, Boolean.class);
        return isValid != null && isValid;
    }
}
