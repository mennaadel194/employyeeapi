package com.rawafed.employeemanagementapi.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentValidationService {

    @Value("${department.validation.api.url}")
    private String departmentValidationApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isValidDepartment(String department) {
        // Call external department validation API
        String url = departmentValidationApiUrl + "?department=" + department;
        Boolean isValid = restTemplate.getForObject(url, Boolean.class);
        return isValid != null && isValid;
    }
}
