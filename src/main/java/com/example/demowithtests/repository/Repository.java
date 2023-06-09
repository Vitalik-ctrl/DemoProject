package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    Employee findByEmail(String email);

    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = "select e from Employee e where e.country =:country")
    List<Employee> findEmployeeByCountry(String country);

    @Query(value = "select e from Employee e join Address a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

    @Query(value = "select * from users where id between ?1 and ?2", nativeQuery = true)
    List<Employee> findEmployeeRangeById(Integer startID, Integer endID);

}
