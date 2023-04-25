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
        Employee employee = Employee.builder()
                .name("Mark")
                .country("England")
                .build();
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
        Assertions.assertThat(employee.getId()).isEqualTo(1);
        Assertions.assertThat(employee.getName()).isEqualTo("Mark");
    }

    @Test
    @Order(2)
    @DisplayName("get employee by id test")
    public void getEmployeeTest() {
        Employee employee = employeeRepository.findById(1).orElseThrow();
        Assertions.assertThat(employee.getId()).isEqualTo(1);
        Assertions.assertThat(employee.getName()).isEqualTo("Mark");
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
        Employee employee = employeeRepository.findById(1).get();
        employee.setName("Martin");
        Employee employeeUpdated = employeeRepository.save(employee);
        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");
    }

//    @Test
//    @Order(5)
//    @Rollback(value = false)
//    public void deleteEmployeeTest() {
//
//        Employee employee = employeeRepository.findById(1).get();
//
//        employeeRepository.delete(employee);
//
//
//        Employee employee1 = null;
//
//        Optional<Employee> optionalAuthor = Optional.ofNullable(employeeRepository.findByName("Martin"));
//
//        if (optionalAuthor.isPresent()) {
//            employee1 = optionalAuthor.get();
//        }
//
//        Assertions.assertThat(employee1).isNull();
//    }

}