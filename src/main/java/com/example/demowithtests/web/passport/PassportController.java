package com.example.demowithtests.web.passport;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PassportController {

    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto savePassport(@RequestBody PassportRequestDto requestDto);

    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.OK)
    List<PassportResponseDto> getAllPassports();

    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto getPassportById(@PathVariable Integer id);

    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto requestDto);

}
