package com.rawafed.employeemanagementapi.config;

import com.rawafed.employeemanagementapi.model.Employee;
import com.rawafed.employeemanagementapi.notification.EmailNotificationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@Configuration
@EnableAsync
public class EmailNotificationConfig {
    private final EmailNotificationService emailNotificationService;

    public EmailNotificationConfig(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @Async
    public void sendEmployeeCreationEmail(Employee employee) {
        try {
            String subject = "Employee Created Successfully";
            String message = "Welcome " + employee.getFirstName() + " " + employee.getLastName();
            emailNotificationService.sendEmail(employee.getEmail(), subject, message);
        } catch (IOException e) {
            throw new RuntimeException("[sendEmail]: Error in method "+e.getMessage());
        }
    }
}
