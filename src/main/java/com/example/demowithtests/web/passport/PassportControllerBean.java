package com.example.demowithtests.web.passport;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.util.mupstruct.PassportMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassportControllerBean implements PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @Override
    public PassportResponseDto savePassport(PassportRequestDto requestDto) {
        Passport passport = passportMapper.fromRequestDto(requestDto);
        return passportMapper.toResponseDto(passportService.create(passport));
    }

    @Override
    public List<PassportResponseDto> getAllPassports() {
        List<Passport> passports = passportService.getAll();
        List<PassportResponseDto> responseDtos = new ArrayList<>();
        for (Passport passport : passports) {
            responseDtos.add(passportMapper.toResponseDto(passport));
        }
        return responseDtos;
    }

    @Override
    public PassportResponseDto getPassportById(Integer id) {
        Passport passport = passportService.getById(id);
        return passportMapper.toResponseDto(passport);
    }

    @Override
    public PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestDto) {
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService.updateById(id, passportMapper.fromRequestDto(requestDto)));
        return responseDto;
    }
}
