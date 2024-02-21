package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.employee.Photo;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
public class EmployeeServiceUtil {

    protected static List<Employee> findEmployeesByLastPhoto(List<Employee> employees, Integer days) {
        List<Employee> employeesToMail = new ArrayList<>();
        for (Employee employee: employees) {
            Set<Photo> photos = employee.getPhotos();
            Date lastPhotoDate = Collections
                    .max(photos, Comparator.comparing(Photo::getDateCreated))
                    .getDateCreated();
            LocalDate photoDate = lastPhotoDate
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate now = Date
                    .from(Instant.now())
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            long daysFromLastPhoto = ChronoUnit.DAYS.between(photoDate, now);
            if (daysFromLastPhoto >= days) {
                employeesToMail.add(employee);
            }
        }
        return employeesToMail;
    }

    protected static Boolean checkEmployeeOnUpdate(Employee employee, String country) {
        return !employee.getCountry().equals(country);
    }

    protected static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        for (Employee employee: employees) {
            emails.add(employee.getEmail());
        }
        return emails;
    }

    protected static void mailSender(List<String> emails, String text) {
        log.debug("Emails were successfully sent.");
    }

    protected static String generateCountry() {
        List<String> countries = new ArrayList<>();
        countries.add("Germany");
        countries.add("Ukraine");
        countries.add("Poland");
        countries.add("USA");
        countries.add("China");
        countries.add("Italy");
        countries.add("The Czech Republic");
        countries.add("Switzerland");
        countries.add("Norway");
        countries.add("Japan");
        Random random = new Random();
        String country = countries.get(random.nextInt(countries.size()));
        return country;
    }
}
