package com.example.demowithtests.util.mupstruct;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.dto.employee.EmployeeRequestDto;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee fromRequestDto(EmployeeRequestDto employeeDto);

    EmployeeRequestDto toRequestDto(Employee employee);

    EmployeeResponseDto toResponseDto(Employee employee);

}
