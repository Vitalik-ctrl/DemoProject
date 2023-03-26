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
    public EmployeeReadDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("--> saveEmployee - start. EmployeeDTO :: \n{}", employeeDto);
        Employee employee = mapper.employeeFromEmployeeDto(employeeDto);
        EmployeeReadDto readDto = mapper.toEmployeeReadDto(service.create(employee));
        log.info("--> saveEmployee - finish. ReadDTO :: \n{}", readDto);
        return readDto;
    }

    @Override
    public List<EmployeeReadDto> getAllUsers() {
        log.info("--> getAllUsers :: start");
        List<Employee> employees = service.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(mapper.toEmployeeReadDto(employee));
        }
        log.info("--> getAllUsers :: finish");
        return employeesReadDto;
    }

    @Override
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.info("--> getEmployeeById :: start");
        EmployeeReadDto employeeReadDto = mapper.toEmployeeReadDto(service.getById(id));
        log.info("--> getEmployeeById :: finish with EmployeeReadDto: {}", employeeReadDto);
        return employeeReadDto;
    }

    @Override
    public EmployeeReadDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
        log.info("--> refreshEmployee :: {}", employeeDto);
        return mapper.toEmployeeReadDto(service.updateById(id, mapper.employeeFromEmployeeDto(employeeDto)));
    }

    @Override
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    @Override
    public void removeAllUsers() {
        service.removeAll();
    }

    @Override
    public void replace() {
        service.processor();
    }

    @Override
    public void sendEmail(@RequestParam String country, @RequestParam String text) {
        service.sendEmailByCountry(country, text);
    }

    @Override
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        service.sendEmailByCountry(city, text);
    }

    @Override
    public void fillData(@RequestParam Integer range, @RequestParam String name, @RequestParam String email) {
        service.fillData(range, name, email);
        log.info("Data was successfully uploaded.");
    }

    @Override
    public void updateByIdRange(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateData(startID, endID);
        log.info("Data was successfully updated.");
    }

    @Override
    public void updateByIdRangePatch(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateCountryDataByPatch(startID, endID);
        log.info("Data was successfully updated.");
    }

    @Override
    public void updateCountryDataByMerge(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateCountryDataByMerge(startID, endID);
        log.info("Data was successfully updated.");
    }

    @Override
    public List<Employee> sendEmail(@RequestParam Integer startID, @RequestParam Integer endID, @RequestParam Integer days, @RequestParam  String text) {
        log.info("Start sendEmail");
        return service.sendEmail(startID, endID, days, text);
    }

    @Override
    public List<Employee> countryChangers(@RequestParam String country) {
        return service.getEmployeeMetrics(country);
    }
}
