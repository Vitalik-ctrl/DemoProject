package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.workplace.State;
import com.example.demowithtests.domain.workplace.Workplace;
import com.example.demowithtests.repository.WorkplaceRepository;
import com.example.demowithtests.util.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WorkplaceServiceBean implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;

    @Override
    public Workplace create(Workplace workplace) {
        log.debug("Service ==> create() - start: workplace = {}", workplace);
        Workplace savedWorkplace = workplaceRepository.save(workplace);
        log.debug("Service ==> create() - end: workplace = {}", savedWorkplace);
        return savedWorkplace;
    }

    @Override
    public Workplace getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        log.debug("Service ==> getById() - start: workplace = {}", workplace);
        return workplace;
    }

    @Override
    public Workplace updateById(Integer id, Workplace workplace) {
        log.debug("Service ==> updateById() - start: id = {}, workplace = {}", id, workplace);
        Workplace existingWorkplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        if (existingWorkplace.equals(workplace)) {
            throw new OldValuesException();
        }
        existingWorkplace.setName(workplace.getName());
        existingWorkplace.setAddress(workplace.getAddress());
        existingWorkplace.setCapacity(workplace.getCapacity());
        Workplace updatedWorkplace = workplaceRepository.save(existingWorkplace);
        log.debug("Service ==> updateById() - end: workplace = {}", updatedWorkplace);
        return updatedWorkplace;
    }

    @Override
    public Workplace changeOpenness(Integer id) {
        log.debug("Service ==> changeOpenness() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        workplace.setIsActive(!workplace.getIsActive());
        workplace.getEmployees().clear();
        log.debug("Service ==> changeOpenness() - end: workplace = {}", workplace);
        return workplaceRepository.save(workplace);
    }

    @Override
    public void removeAll() {
        log.debug("Service ==> removeAll() - start: ");
        if (workplaceRepository.findAll().size() == 0) {
            throw new DataIsEmptyException();
        }
        try {
            workplaceRepository.deleteAll();
        } catch (Exception e) {
            throw new UnhandledException();
        }
        log.debug("Service ==> removeAll() - end: ");
    }

    @Override
    public Workplace checkState(Integer id) {
        log.debug("Service ==> checkState() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        State state = workplace.getState();
        switch (state) {
            case GOOD:
                break;
            case NORMAL:
                workplace.setState(State.GOOD);
                break;
            case BAD:
                workplace.setState(State.NORMAL);
                break;
        }
        log.debug("Service ==> checkState() - end: workplace = {}", workplace);
        return workplaceRepository.save(workplace);
    }

    @Override
    public Workplace updateCapacity(Integer id) {
        log.debug("Service ==> updateCapacity() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        workplace.setCapacity(workplace.getCapacity() * 2);
        log.debug("Service ==> updateCapacity() - end: workplace = {}", workplace);
        return workplaceRepository.save(workplace);
    }
}