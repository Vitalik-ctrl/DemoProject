package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.workplace.Workplace;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> processor();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    void fillData(Integer range, String name, String email);

    void updateData(Integer startID, Integer endID);

    void updateCountryDataByPatch(Integer startID, Integer endID);

    void updateCountryDataByMerge(Integer startID, Integer endID);

    List<Employee> sendEmail(Integer startID, Integer endID, Integer days, String text);

    List<Employee> getEmployeeMetrics(String country);

    Employee addPassport(Integer employeeId, Integer passportId);

    Employee addWorkplace(Integer employeeId, Integer workspaceId);

    Set<Workplace> getWorkplaces(Integer id);

    Employee reconnectToWorkplace(Integer id);

    Employee findBestWorkplaces(Integer id);

    Employee save(Employee employee);

    Employee find(Integer id);

    Employee update(Integer id, Employee employee);

    void detach(Integer id);
}
