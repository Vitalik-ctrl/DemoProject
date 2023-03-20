package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.service.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    //  public Employee getEmployeeById(@RequestParam("id") Integer id) {
    public Employee getEmployeeById(@PathVariable Integer id) {

        Employee employee = service.getById(id);
        return employee;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return service.updateById(id, employee);
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

}
