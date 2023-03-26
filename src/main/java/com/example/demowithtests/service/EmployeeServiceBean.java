package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class EmployeeServiceBean implements EmployeeService {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        log.debug("Service ==> create() - start: employee = {}", employee);
        if (repository.findByEmail(employee.getEmail()) != null) {
            throw new EmailDuplicatedException();
        }
        if (employee.getName().equals("")) {
            throw new NameIsEmptyException();
        }
        Employee createdEmployee = repository.save(employee);
        log.debug("Service ==> create() - end: employee = {}", createdEmployee);
        return createdEmployee;
    }

    @Override
    public List<Employee> getAll() {
        log.debug("Service ==> getAll() - start: ");
        if (repository.findAll().size() == 0) {
            throw new DataIsEmptyException();
        }
        try {
            List<Employee> allEmployees = repository.findAll();
            log.debug("Service ==> getAll() - end: ");
            return allEmployees;
        } catch (Exception e) {
            throw new UnhandledException();
        }
    }

    @Override
    public Employee getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        var employee = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new ResourceWasDeletedException();
        }
        log.debug("Service ==> getById() - end: employee = {}", employee);
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        log.debug("Service ==> updateById() - start: id = {}, employee = {}", id, employee);
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        if (existingEmployee.equals(employee)) {
            throw new OldValuesException();
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setCountry(employee.getCountry());
        Employee updatedEmployee = repository.save(existingEmployee);
        log.debug("Service ==> updateById() - end: employee = {}", updatedEmployee);
        return updatedEmployee;
    }

    @Override
    public void removeById(Integer id) {
        log.debug("Service ==> removeById() - start: id = {}", id);
        var employee = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        if (employee.getIsDeleted()) {
            throw new EmployeeWasAlreadyDeleted();
        }
        employee.setIsDeleted(true);
        repository.save(employee);
        log.debug("Service ==> removeById() - end: deleted employee = {}", employee);
    }

    @Override
    public void removeAll() {
        log.debug("Service ==> removeAll() - start: ");
        if (repository.findAll().size() == 0) {
            throw new DataIsEmptyException();
        }
        try {
            repository.deleteAll();
        } catch (Exception e) {
            throw new UnhandledException();
        }
        log.debug("Service ==> removeAll() - end: ");
    }

    @Override
    public List<Employee> processor() {
        log.debug("Service ==> processor() - start: ");
        List<Employee> replaceNull = repository.findEmployeeByIsDeletedNull();
        for (Employee employee : replaceNull) {
            employee.setIsDeleted(Boolean.FALSE);
        }
        log.debug("Service ==> processor() - end: collection = {}", replaceNull);
        return repository.saveAll(replaceNull);
    }

    @Override
    public List<Employee> sendEmailByCountry(String country, String text) {
        log.debug("Service ==> sendEmailByCountry() - start: country = {}", country);
        List<Employee> employees = repository.findEmployeeByCountry(country);
        EmployeeServiceUtil.mailSender(EmployeeServiceUtil.extracted(employees), text);
        log.debug("Service ==> sendEmailByCountry() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public List<Employee> sendEmailByCity(String city, String text) {
        log.debug("Service ==> sendEmailByCity() - start: city = {}", city);
        List<Employee> employees = repository.findEmployeeByAddresses(city);
        EmployeeServiceUtil.mailSender(EmployeeServiceUtil.extracted(employees), text);
        log.debug("Service ==> sendEmailByCity() - end: employees = {}", employees);
        return employees;
    }

    // Improved method
    @Override
    public void fillData(Integer range, String name, String email) {
        log.debug("Service ==> fillData() - start: range = {}, name = {}, email = {}", range, name, email);
        for (int i = 0; i < range; i++) {
            Employee employee = new Employee(name, EmployeeServiceUtil.generateCountry(), email, Boolean.FALSE);
            repository.save(employee);
        }
        log.debug("Service ==> fillData() - end: ");
    }

    // Old Method
    @Override
    public void updateData(Integer startID, Integer endID) {
        log.debug("Service ==> updateData() - start: range = [{},{}]", startID, endID);
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        for (Employee employee : employees) {
            employee.setCountry("Denmark");
        }
        repository.saveAll(employees);
        log.debug("Service ==> updateData() - end: employees = {}", employees);
    }

    // Improved method
    @Override
    public void updateCountryDataByPatch(Integer startID, Integer endID) {
        log.debug("Service ==> updateCountryDataByPatch() - start: range = [{},{}]", startID, endID);
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        for (Employee employee : employees) {
            String generatedCountry = EmployeeServiceUtil.generateCountry();
            log.info("Generated Country={}", generatedCountry);
            if (EmployeeServiceUtil.checkEmployeeOnUpdate(employee, generatedCountry)) {
                employee.setCountry(generatedCountry);
            }
        }
        repository.saveAll(employees);
        log.debug("Service ==> updateCountryDataByPatch() - end: employees = {}", employees);
    }

    @Override
    public void updateCountryDataByMerge(Integer startID, Integer endID) {
        log.debug("Service ==> updateCountryDataByMerge() - start: range = [{},{}]", startID, endID);
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        repository.saveAll(employees.stream()
                .map(e -> new Employee(e.getName(), e.getEmail(), EmployeeServiceUtil.generateCountry(), Boolean.FALSE))
                .collect(Collectors.toList()));
        log.debug("Service ==> updateCountryDataByMerge() - end: employees = {}", employees);
    }

    @Override
    public List<Employee> sendEmail(Integer startID, Integer endID, Integer days, String text) {
        log.debug("Service ==> sendEmail() - start: range = [{},{}], days = {}", startID, endID, days);
        List<Employee> employees = repository.findEmployeeRangeById(startID, endID);
        List<Employee> employeesToMail = EmployeeServiceUtil.findEmployeesByLastPhoto(employees, days);
        EmployeeServiceUtil.mailSender(EmployeeServiceUtil.extracted(employeesToMail), text);
        log.debug("Service ==> sendEmail() - end: employees = {}", employees);
        return employeesToMail;
    }

    @Override
    public List<Employee> getEmployeeMetrics(String country) {
        log.debug("Service ==> getEmployeeMetrics() - start: country = {}", country);
        List<Employee> employees = repository.findEmployeesByAddressCountry(country);
        var addresses = employees.stream()
                .filter(employee -> employee.getAddresses().stream()
                        .anyMatch(address -> address.getCountry().equals(country) && !address.getAddressHasActive()))
                .collect(Collectors.toList());
        EmployeeServiceUtil.mailSender(EmployeeServiceUtil.extracted(addresses), "russia will be destroyed by Ukrainians");
        log.debug("Service ==> getEmployeeMetrics() - end: employees = {}", employees);
        return addresses;
    }
}
