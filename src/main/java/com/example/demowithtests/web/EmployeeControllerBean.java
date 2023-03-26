package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.EmployeeService;
import com.example.demowithtests.util.mupstruct.Mapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final EmployeeService service;
    private final Mapper mapper;

    @Override
    public EmployeeReadDto saveEmployee(EmployeeDto employeeDto) {
        log.info("Controller ==> saveEmployee() - start: employeeDto = {}", employeeDto);
        Employee employee = mapper.employeeFromEmployeeDto(employeeDto);
        EmployeeReadDto readDto = mapper.toEmployeeReadDto(service.create(employee));
        log.info("Controller ==> saveEmployee() - end: employeeReadDto = {}", readDto);
        return readDto;
    }

    @Override
    public List<EmployeeReadDto> getAllUsers() {
        log.info("Controller ==> getAllUsers() - start: ");
        List<Employee> employees = service.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(mapper.toEmployeeReadDto(employee));
        }
        log.info("Controller ==> getAllUsers() - end: ");
        return employeesReadDto;
    }

    @Override
    public EmployeeReadDto getEmployeeById(Integer id) {
        log.info("Controller ==> getEmployeeById() - start: id = {}", id);
        EmployeeReadDto employeeReadDto = mapper.toEmployeeReadDto(service.getById(id));
        log.info("Controller ==> getEmployeeById() - end: employeeReadDto = {}", employeeReadDto);
        return employeeReadDto;
    }

    @Override
    public EmployeeReadDto refreshEmployee(Integer id, EmployeeDto employeeDto) {
        log.info("Controller ==> refreshEmployee() - start: id = {}, employeeDto = {}", id, employeeDto);
        EmployeeReadDto employeeReadDto = mapper.toEmployeeReadDto(service.updateById(id, mapper.employeeFromEmployeeDto(employeeDto)));
        log.info("Controller ==> refreshEmployee() - end: id = {}, employeeReadDto = {}", id, employeeReadDto);
        return employeeReadDto;
    }

    @Override
    public void removeEmployeeById(Integer id) {
        log.info("Controller ==> removeEmployeeById() - start: id = {}", id);
        service.removeById(id);
        log.info("Controller ==> removeEmployeeById() - end: ");
    }

    @Override
    public void removeAllUsers() {
        log.info("Controller ==> removeAllUsers() - start: ");
        service.removeAll();
        log.info("Controller ==> removeAllUsers() - end: ");
    }

    @Override
    public void replace() {
        log.info("Controller ==> replace() - start: ");
        service.processor();
        log.info("Controller ==> replace() - end: ");
    }

    @Override
    public void sendEmail(String country, String text) {
        log.info("Controller ==> sendEmail() - start: country = {}", country);
        service.sendEmailByCountry(country, text);
        log.info("Controller ==> sendEmail() - end: ");
    }

    @Override
    public void sendEmailByCity(String city, String text) {
        log.info("Controller ==> sendEmailByCity() - start: city = {}", city);
        service.sendEmailByCountry(city, text);
        log.info("Controller ==> sendEmailByCity() - end: ");
    }

    @Override
    public void fillData(Integer range, String name, String email) {
        log.info("Controller ==> fillData() - start: range = {}, name = {}, email = {}", range, name, email);
        service.fillData(range, name, email);
        log.info("Controller ==> fillData() - end: ");
    }

    @Override
    public void updateByIdRange(Integer startID, Integer endID) {
        log.info("Controller ==> updateByIdRange() - start: range = [{},{}]", startID, endID);
        service.updateData(startID, endID);
        log.info("Controller ==> updateByIdRange() - end: ");
    }

    @Override
    public void updateByIdRangePatch(Integer startID, Integer endID) {
        log.info("Controller ==> updateByIdRangePatch() - start: range = [{},{}]", startID, endID);
        service.updateCountryDataByPatch(startID, endID);
        log.info("Controller ==> updateByIdRangePatch() - end: ");
    }

    @Override
    public void updateCountryDataByMerge(Integer startID, Integer endID) {
        log.info("Controller ==> updateCountryDataByMerge() - start: range = [{},{}]", startID, endID);
        service.updateCountryDataByMerge(startID, endID);
        log.info("Controller ==> updateCountryDataByMerge() - end: ");
    }

    @Override
    public List<Employee> sendEmail(Integer startID, Integer endID, Integer days, String text) {
        log.info("Controller ==> sendEmail() - start: range = [{},{}], days = {}", startID, endID, days);
        List<Employee> employees = service.sendEmail(startID, endID, days, text);
        log.info("Controller ==> sendEmail() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public List<Employee> countryChangers(String country) {
        log.info("Controller ==> countryChangers() - start: country = {}", country);
        List<Employee> employees = service.getEmployeeMetrics(country);
        log.info("Controller ==> countryChangers() - end: employees = {}", employees);
        return employees;
    }
}
