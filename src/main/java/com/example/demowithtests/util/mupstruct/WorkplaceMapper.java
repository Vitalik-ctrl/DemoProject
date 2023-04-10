package com.example.demowithtests.util.mupstruct;

import com.example.demowithtests.domain.workplace.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {

    Workplace fromRequestDto(WorkplaceRequestDto workplaceRequestDto);
    WorkplaceResponseDto toResponseDto(Workplace workplace);

}