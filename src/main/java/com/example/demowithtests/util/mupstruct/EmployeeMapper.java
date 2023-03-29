package com.example.demowithtests.util.mupstruct;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    @Mapping(target = "name", source = "employeeDto.name")
//    @Mapping(target = "email", source = "employeeDto.email")
//    @Mapping(target = "country", source = "employeeDto.country")
//    @Mapping(target = "addresses", source = "employeeDto.addresses")
//    @Mapping(target = "photos", source = "employeeDto.photos")
    Employee fromRequestDto(EmployeeRequestDto employeeDto);

//    @Mapping(target = "name", source = "employee.name")
//    @Mapping(target = "email", source = "employee.email")
//    @Mapping(target = "country", source = "employee.country")
//    @Mapping(target = "photos", source = "employee.photos")
//    @Mapping(target = "addresses", source = "employee.addresses")
    EmployeeRequestDto toRequestDto(Employee employee);

//    @Mapping(target = "name", source = "employee.name")
//    @Mapping(target = "email", source = "employee.email")
//    @Mapping(target = "photos", source = "employee.photos")
//    @Mapping(target = "addresses", source = "employee.addresses")
//    @Mapping(target = "passport", source = "employee.passport")
    EmployeeResponseDto toResponseDto(Employee employee);

}
