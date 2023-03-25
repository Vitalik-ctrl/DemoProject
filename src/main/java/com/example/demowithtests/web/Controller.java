package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.mupstruct.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;
    private final Mapper mapper;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeReadDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("--> saveEmployee - start. EmployeeDTO :: \n{}", employeeDto);
        Employee employee = mapper.employeeFromEmployeeDto(employeeDto);
        EmployeeReadDto readDto = mapper.toEmployeeReadDto(service.create(employee));
        log.info("--> saveEmployee - finish. ReadDTO :: \n{}", readDto);
        return readDto;
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
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

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.info("--> getEmployeeById :: start");
        EmployeeReadDto employeeReadDto = mapper.toEmployeeReadDto(service.getById(id));
        log.info("--> getEmployeeById :: finish with EmployeeReadDto: {}", employeeReadDto);
        return employeeReadDto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
        log.info("--> refreshEmployee :: {}", employeeDto);
        return mapper.toEmployeeReadDto(service.updateById(id, mapper.employeeFromEmployeeDto(employeeDto)));
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replace() {
        service.processor();
    }

    @PostMapping("/sendEmail")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestParam String country, @RequestParam String text) {
        service.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        service.sendEmailByCountry(city, text);
    }

    @GetMapping("/fillData")
    @ResponseStatus(HttpStatus.OK)
    public void fillData(@RequestParam Integer range, @RequestParam String name, @RequestParam String email) {
        service.fillData(range, name, email);
        log.info("Data was successfully uploaded.");
    }

    @PutMapping("/updateUsersRange")
    @ResponseStatus(HttpStatus.OK)
    public void updateByIdRange(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateData(startID, endID);
        log.info("Data was successfully updated.");
    }

    @PutMapping("/updateUsersRangePatch")
    @ResponseStatus(HttpStatus.OK)
    public void updateByIdRangePatch(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateCountryDataByPatch(startID, endID);
        log.info("Data was successfully updated.");
    }

    @PutMapping("/updateCountryDataByMerge")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountryDataByMerge(@RequestParam Integer startID, @RequestParam Integer endID) {
        service.updateCountryDataByMerge(startID, endID);
        log.info("Data was successfully updated.");
    }

    @PostMapping("/sendEmailByLastPhoto")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> sendEmail(@RequestParam Integer startID, @RequestParam Integer endID, @RequestParam Integer days, @RequestParam  String text) {
        log.info("Start sendEmail");
        return service.sendEmail(startID, endID, days, text);
    }
}
