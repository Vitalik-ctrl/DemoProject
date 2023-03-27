package com.example.demowithtests.util.mupstruct;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    @Mapping(target = "name", source = "employeeDto.name")
//    @Mapping(target = "email", source = "employeeDto.email")
//    @Mapping(target = "country", source = "employeeDto.country")
//    @Mapping(target = "addresses", source = "employeeDto.addresses")
//    @Mapping(target = "photos", source = "employeeDto.photos")
    Employee employeeFromEmployeeDto(EmployeeDto employeeDto);

//    @Mapping(target = "name", source = "employee.name")
//    @Mapping(target = "email", source = "employee.email")
//    @Mapping(target = "country", source = "employee.country")
//    @Mapping(target = "photos", source = "employee.photos")
//    @Mapping(target = "addresses", source = "employee.addresses")
    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(target = "name", source = "employee.name")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "photos", source = "employee.photos")
    @Mapping(target = "addresses", source = "employee.addresses")
    @Mapping(target = "passport", source = "employee.passport")
    EmployeeReadDto toEmployeeReadDto(Employee employee);

}
