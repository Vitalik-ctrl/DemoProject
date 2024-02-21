package com.example.demowithtests.util.mupstruct;

import com.example.demowithtests.domain.passport.Registration;
import com.example.demowithtests.dto.passport.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration fromRegistrationDto(RegistrationDto registrationDto);

    RegistrationDto toRegistrationDto(Registration registration);
}
