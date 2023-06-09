package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
//        if (repository.findByEmail(employee.getEmail()) != null) {
//            throw new EmailDuplicatedException();
//        }
        if (employee.getName().equals("")) {
            throw new NameIsEmptyException();
        }
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        if (repository.findAll().size() == 0) {
            throw new DataIsEmptyException();
        }
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new UnhandledException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        log.info("getByID ---");
        var employee = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
       // if (employee.getIsDeleted()) {
       //     throw new ResourceWasDeletedException();
       // }
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        if (repository.findById(id).isPresent()) {
            if (repository.findById(id).get().equals(employee)) {
                throw new OldValuesException();
            }
            return repository.findById(id)
                    .map(entity -> {
                        entity.setName(employee.getName());
                        entity.setEmail(employee.getEmail());
                        entity.setCountry(employee.getCountry());
                        return repository.save(entity);
                    }).get();
        } else {
            throw new IdNotFoundException();
        }
    }

    @Override
    public void removeById(Integer id) {
        var employee = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new EmployeeWasAlreadyDeleted();
        }
        employee.setIsDeleted(true);
        repository.save(employee);
    }

    @Override
    public void removeAll() {
        if (repository.findAll().size() == 0) {
            throw new DataIsEmptyException();
        }

        try {
            repository.deleteAll();
        } catch (Exception e) {
            throw new UnhandledException();
        }

    }

    @Override
    public List<Employee> processor() {
        log.info("replace null - start");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        for (Employee employee: replaceNull) {
            employee.setIsDeleted(Boolean.FALSE);
        }
        log.info("replaced collection = {}", replaceNull);
        log.info("replace null - finish");
        return repository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        List<Employee> employees = repository.findEmployeeByCountry(country);
        mailSender(extracted(employees), text);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        List<Employee> employees = repository.findEmployeeByAddresses(city);
        mailSender(extracted(employees), text);
        return employees;
    }

    // Improved method
    @Override
    public void fillData(Integer range, String name, String email) {
        for (int i = 0; i < range; i++) {
            Employee employee = new Employee(name, generateCountry(), email, Boolean.FALSE);
            repository.save(employee);
        }
    }

    // Old Method
    @Override
    public void updateData(Integer startID, Integer endID) {
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        for (Employee employee: employees) {
            employee.setCountry("Denmark");
        }
        repository.saveAll(employees);
    }

    // Improved method
    @Override
    public void updateCountryDataByPatch(Integer startID, Integer endID) {
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        for (Employee employee: employees) {
            String generatedCountry = generateCountry();
            log.info("Generated Country={}", generatedCountry);
            if (checkEmployeeOnUpdate(employee, generatedCountry)) {
                employee.setCountry(generatedCountry);
            }
        }
        repository.saveAll(employees);
    }

    @Override
    public void updateCountryDataByMerge(Integer startID, Integer endID) {
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        repository.saveAll(employees.stream()
                .map(e -> new Employee(e.getName(), e.getEmail(), generateCountry(), Boolean.FALSE))
                .collect(Collectors.toList()));
    }

    private static Boolean checkEmployeeOnUpdate(Employee employee, String country) {
        return !employee.getCountry().equals(country);
    }

    private static List<String> extracted(List<Employee> employees) {
        List<String> emails = new ArrayList<>();
        for (Employee employee: employees) {
            emails.add(employee.getEmail());
        }
        return emails;
    }

    public void mailSender(List<String> emails, String text) {
        log.info("Emails were successfully sent.");
    }

    public String generateCountry() {
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
