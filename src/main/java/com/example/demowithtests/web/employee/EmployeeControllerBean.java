package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.workplace.Workplace;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.mupstruct.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto) {
        log.info("Controller ==> saveEmployee() - start: employeeRequestDto = {}", employeeRequestDto);
        Employee employee = employeeMapper.fromRequestDto(employeeRequestDto);
        EmployeeResponseDto responseDto = employeeMapper.toResponseDto(employeeService.create(employee));
        log.info("Controller ==> saveEmployee() - end: employeeReadDto = {}", responseDto);
        return responseDto;
    }

    @Override
    public List<EmployeeResponseDto> getAllUsers() {
        log.info("Controller ==> getAllUsers() - start: ");
        List<Employee> employees = employeeService.getAll();
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponseDtoList.add(employeeMapper.toResponseDto(employee));
        }
        log.info("Controller ==> getAllUsers() - end: ");
        return employeeResponseDtoList;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Integer id) {
        log.info("Controller ==> getEmployeeById() - start: id = {}", id);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employeeService.getById(id));
        log.info("Controller ==> getEmployeeById() - end: employeeReadDto = {}", employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto refreshEmployee(Integer id, EmployeeRequestDto employeeRequestDto) {
        log.info("Controller ==> refreshEmployee() - start: id = {}, employeeDto = {}", id, employeeRequestDto);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employeeService.updateById(id, employeeMapper.fromRequestDto(employeeRequestDto)));
        log.info("Controller ==> refreshEmployee() - end: id = {}, employeeReadDto = {}", id, employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public void removeEmployeeById(Integer id) {
        log.info("Controller ==> removeEmployeeById() - start: id = {}", id);
        employeeService.removeById(id);
        log.info("Controller ==> removeEmployeeById() - end: ");
    }

    @Override
    public void removeAllUsers() {
        log.info("Controller ==> removeAllUsers() - start: ");
        employeeService.removeAll();
        log.info("Controller ==> removeAllUsers() - end: ");
    }

    @Override
    public void replace() {
        log.info("Controller ==> replace() - start: ");
        employeeService.processor();
        log.info("Controller ==> replace() - end: ");
    }

    @Override
    public void sendEmail(String country, String text) {
        log.info("Controller ==> sendEmail() - start: country = {}", country);
        employeeService.sendEmailByCountry(country, text);
        log.info("Controller ==> sendEmail() - end: ");
    }

    @Override
    public void sendEmailByCity(String city, String text) {
        log.info("Controller ==> sendEmailByCity() - start: city = {}", city);
        employeeService.sendEmailByCountry(city, text);
        log.info("Controller ==> sendEmailByCity() - end: ");
    }

    @Override
    public void fillData(Integer range, String name, String email) {
        log.info("Controller ==> fillData() - start: range = {}, name = {}, email = {}", range, name, email);
        employeeService.fillData(range, name, email);
        log.info("Controller ==> fillData() - end: ");
    }

    @Override
    public void updateByIdRange(Integer startID, Integer endID) {
        log.info("Controller ==> updateByIdRange() - start: range = [{},{}]", startID, endID);
        employeeService.updateData(startID, endID);
        log.info("Controller ==> updateByIdRange() - end: ");
    }

    @Override
    public void updateByIdRangePatch(Integer startID, Integer endID) {
        log.info("Controller ==> updateByIdRangePatch() - start: range = [{},{}]", startID, endID);
        employeeService.updateCountryDataByPatch(startID, endID);
        log.info("Controller ==> updateByIdRangePatch() - end: ");
    }

    @Override
    public void updateCountryDataByMerge(Integer startID, Integer endID) {
        log.info("Controller ==> updateCountryDataByMerge() - start: range = [{},{}]", startID, endID);
        employeeService.updateCountryDataByMerge(startID, endID);
        log.info("Controller ==> updateCountryDataByMerge() - end: ");
    }

    @Override
    public List<Employee> sendEmail(Integer startID, Integer endID, Integer days, String text) {
        log.info("Controller ==> sendEmail() - start: range = [{},{}], days = {}", startID, endID, days);
        List<Employee> employees = employeeService.sendEmail(startID, endID, days, text);
        log.info("Controller ==> sendEmail() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public List<Employee> countryChangers(String country) {
        log.info("Controller ==> countryChangers() - start: country = {}", country);
        List<Employee> employees = employeeService.getEmployeeMetrics(country);
        log.info("Controller ==> countryChangers() - end: employees = {}", employees);
        return employees;
    }

    @Override
    public EmployeeResponseDto addPassport(Integer employeeId, Integer passportId) {
        log.info("Controller ==> addPassport() - start: employeeId = {}, passportId = {}", employeeId, passportId);
        Employee employee = employeeService.addPassport(employeeId, passportId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employee);
        log.info("Controller ==> addPassport() - end: employee = {}", employeeResponseDto);
        return employeeResponseDto;
    }


    public EmployeeResponseDto addPassportSafely(Integer employeeId, Integer passportId){
        log.info("Controller ==> addPassportSafely() - start: employeeId = {}, passportId = {}", employeeId, passportId);
        Employee employee = employeeService.addPassport(employeeId, passportId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employee);
        log.info("Controller ==> addPassportSafely() - end: employee = {}", employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto addWorkplace(Integer employeeId, Integer workplaceId) {
        log.info("Controller ==> addWorkplace() - start: employeeId = {}, workplaceId = {}", employeeId, workplaceId);
        Employee employee = employeeService.addWorkplace(employeeId, workplaceId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employee);
        log.info("Controller ==> addWorkplace() - end: employee = {}", employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public Set<Workplace> getWorkplaces(Integer id) {
        log.info("Controller ==> getWorkplaces() - start: id = {}", id);
        Set<Workplace> workplaces = employeeService.getWorkplaces(id);
        log.info("Controller ==> getWorkplaces() - end: workplaces = {}", workplaces);
        return workplaces;
    }

    @Override
    public EmployeeResponseDto reconnectToWorkplace(Integer id) {
        log.info("Controller ==> reconnectToWorkplace() - start: id = {}", id);
        Employee employee = employeeService.reconnectToWorkplace(id);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employee);
        log.info("Controller ==> reconnectToWorkplace() - end: employee = {}", employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto findBestWorkplaces(Integer id) {
        log.info("Controller ==> findBestWorkplaces() - start: id = {}", id);
        Employee employee = employeeService.findBestWorkplaces(id);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toResponseDto(employee);
        log.info("Controller ==> findBestWorkplaces() - end: employee = {}", employeeResponseDto);
        return employeeResponseDto;
    }
}
