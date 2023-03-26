package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EmployeeController {

    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists.")})
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeReadDto saveEmployee(@RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to get all employees.", description = "Create request to get all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. List of all employees was received."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<EmployeeReadDto> getAllUsers();

    @Operation(summary = "This is endpoint to get employee by ID.", description = "Create request to get employee by ID.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Employee was received."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Employee does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto getEmployeeById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to refresh employee by ID & new body.", description = "Create request to refresh employee by ID and body.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Employee was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Employee does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto);

    @Operation(summary = "This is endpoint to remove employee by ID.", description = "Create request to remove employee by ID.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESS. Employee was deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Employee does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeEmployeeById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to remove all employees.", description = "Create request to remove employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESS. All employees were deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAllUsers();

    @Operation(summary = "This is endpoint to replace nulls.", description = "Create request to replace nulls.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. All nulls were replaced."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    void replace();

    @Operation(summary = "This is endpoint to send email by country.", description = "Create request to send emails.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. All emails were sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PostMapping("/sendEmail")
    @ResponseStatus(HttpStatus.OK)
    void sendEmail(@RequestParam String country, @RequestParam String text);

    @Operation(summary = "This is endpoint to send email by city.", description = "Create request to send emails.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. All emails were sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailByCity(@RequestParam String city, @RequestParam String text);

    @Operation(summary = "This is endpoint to fill database by common employees.", description = "Create request to fill DB.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. All employees were added."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/fillData")
    @ResponseStatus(HttpStatus.OK)
    void fillData(@RequestParam Integer range, @RequestParam String name, @RequestParam String email);

    @Operation(summary = "This is endpoint to set one country for all employees in range.", description = "Create request to set country to employees in range.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Country was changed for all employees in range."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/updateUsersRange")
    @ResponseStatus(HttpStatus.OK)
    void updateByIdRange(@RequestParam Integer startID, @RequestParam Integer endID);

    @Operation(summary = "This is endpoint to set randomly generated country for all employees in range.", description = "Create request to set country to employees in range.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Country was changed for all employees in range."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/updateUsersRangePatch")
    @ResponseStatus(HttpStatus.OK)
    void updateByIdRangePatch(@RequestParam Integer startID, @RequestParam Integer endID);

    @Operation(summary = "This is endpoint to smart change one country for all employees in range.", description = "Create request to set country to employees in range.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Country was changed for all employees in range."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/updateCountryDataByMerge")
    @ResponseStatus(HttpStatus.OK)
    void updateCountryDataByMerge(@RequestParam Integer startID, @RequestParam Integer endID);

    @Operation(summary = "This is endpoint to send mail to employee if his last photo too old.", description = "Create request to send mail.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Mails were sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PostMapping("/sendEmailByLastPhoto")
    @ResponseStatus(HttpStatus.OK)
    List<Employee> sendEmail(@RequestParam Integer startID, @RequestParam Integer endID, @RequestParam Integer days, @RequestParam  String text);

    @Operation(summary = "This is endpoint to get employees who changed country & send them mail.", description = "Create request to get users.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Employees were received, mails were sent."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @GetMapping("/countryChangers")
    @ResponseStatus(HttpStatus.OK)
    List<Employee> countryChangers(@RequestParam String country);
}
