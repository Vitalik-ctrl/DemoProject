package com.example.demowithtests.service.workplace;

import com.example.demowithtests.domain.workplace.Workplace;

public interface WorkplaceService {

    Workplace create(Workplace workplace);

    Workplace getById(Integer id);

    Workplace updateById(Integer id, Workplace workplace);

    Workplace changeOpenness(Integer id);

    void removeAll();

    Workplace checkState(Integer id);

    Workplace updateCapacity(Integer id);
}