package com.example.demowithtests;


import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Employee Repository Tests")
public class RepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("save employee test")
    public void saveEmployeeTest() {
        Employee johnDoe = Employee.builder()
                .name("John")
                .country("England")
                .build();
        employeeRepository.save(johnDoe);
        Assertions.assertThat(johnDoe.getId()).isGreaterThan(0);
        Assertions.assertThat(johnDoe.getId()).isEqualTo(1);
        Assertions.assertThat(johnDoe.getName()).isEqualTo("John");
    }

    @Test
    @Order(2)
    @DisplayName("get employee by id test")
    public void getEmployeeTest() {
        Employee johnDoe = employeeRepository.findById(1).orElseThrow();
        Assertions.assertThat(johnDoe.getId()).isEqualTo(1);
        Assertions.assertThat(johnDoe.getName()).isEqualTo("John");
    }

    @Test
    @Order(3)
    public void getListOfEmployeeTest() {
        List<Employee> employeesList = employeeRepository.findAll();
        Assertions.assertThat(employeesList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Employee johnDoe = employeeRepository.findById(1).get();
        johnDoe.setName("Martin");
        Employee employeeUpdated = employeeRepository.save(johnDoe);
        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void findByEmail_shouldReturnEmployee() {
        String email = "john.doe@example.com";
        Employee johnDoe = new Employee("John", "Doe", email, null);
        employeeRepository.save(johnDoe);
        Employee result = employeeRepository.findByEmail(email);
        Assertions.assertThat(result).isEqualTo(johnDoe);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void findEmployeeByIsDeletedNull_shouldReturnNonDeletedEmployees() {
        Employee johnDoe = new Employee("John", "Doe", "john.doe@example.com", null);
        employeeRepository.save(johnDoe);
        Employee janeDoe = new Employee("Jane", "Doe", "jane.doe@example.com", false);
        employeeRepository.save(janeDoe);
        List<Employee> result = employeeRepository.findEmployeeByIsDeletedNull();
        Assertions.assertThat(result).containsOnly(johnDoe);
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void findEmployeeByCountry_shouldReturnEmployeesInCountry() {
        Employee johnDoe = new Employee("John", "Doe", "john.doe@example.com", false);
        johnDoe.setCountry("USA");
        employeeRepository.save(johnDoe);
        Employee janeDoe = new Employee("Jane", "Doe", "jane.doe@example.com", false);
        janeDoe.setCountry("Canada");
        employeeRepository.save(janeDoe);
        List<Employee> result = employeeRepository.findEmployeeByCountry("USA");
        Assertions.assertThat(result).containsOnly(johnDoe);
    }

    @Test
    @Order(8)
    @Rollback(value = false)
    public void findEmployeeRangeById_shouldReturnEmployeesInRange() {
        Employee johnDoe = new Employee("John", "Doe", "john.doe@example.com", false);
        employeeRepository.save(johnDoe);
        Employee janeDoe = new Employee("Jane", "Doe", "jane.doe@example.com", false);
        employeeRepository.save(janeDoe);
        Employee aliceSmith = new Employee("Alice", "Smith", "alice.smith@example.com", false);
        employeeRepository.save(aliceSmith);
        List<Employee> result = employeeRepository.findEmployeeRangeById(1, 3);
        Assertions.assertThat(result).containsOnly(johnDoe, janeDoe, aliceSmith);
    }





}