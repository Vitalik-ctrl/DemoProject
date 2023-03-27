package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.exceptions.IdNotFoundException;
import com.example.demowithtests.util.exceptions.OldValuesException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAll() {
        return passportRepository.findAll();
    }

    @Override
    public Passport getById(Integer id) {
        return passportRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    public Passport updateById(Integer id, Passport passport) {
        Passport entity = passportRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        entity.setFirstName(passport.getFirstName());
        entity.setSecondName(passport.getSecondName());
        Passport updatePassport = passportRepository.save(entity);
        return updatePassport;
    }

}
