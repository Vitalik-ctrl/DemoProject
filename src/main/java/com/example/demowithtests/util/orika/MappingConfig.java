package com.example.demowithtests.util.orika;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {

        mapperFactory.classMap(Employee.class, EmployeeDto.class)
                .customize(new EmployeeMapper())
                .byDefault()
                .register();

        mapperFactory.classMap(Employee.class, EmployeeDto.class)
                .byDefault()
                .register();
    }
}
