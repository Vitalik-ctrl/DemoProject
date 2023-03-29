package com.example.demowithtests.repository;

import com.example.demowithtests.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);

    List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = "select e from Employee e where e.country =:country")
    List<Employee> findEmployeeByCountry(String country);

    @Query(value = "select e from Employee e join Address a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

    @Query(value = "select * from users where id between ?1 and ?2", nativeQuery = true)
    List<Employee> findEmployeeRangeById(Integer startID, Integer endID);

    @Query(value = "select * from users join addresses on addresses.employee_id=users.id where addresses.country=:country", nativeQuery = true)
  //  @Query(value = "select e from Employee e join Address a where a.addressHasActive=false and a.country=:country")
    List<Employee> findEmployeesByAddressCountry(String country);

}
