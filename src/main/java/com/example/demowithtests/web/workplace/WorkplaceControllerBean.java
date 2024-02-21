package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.workplace.Workplace;
import com.example.demowithtests.dto.employee.EmployeeResponseDto;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.mupstruct.WorkplaceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Workplace", description = "Workplace API")
public class WorkplaceControllerBean implements WorkplaceController {

    private final WorkplaceService workplaceService;
    private final WorkplaceMapper workplaceMapper;

    @Override
    public WorkplaceResponseDto saveWorkplace(WorkplaceRequestDto workplaceRequestDto) {
        log.info("Controller ==> saveWorkplace() - start: workplace = {}", workplaceRequestDto);
        Workplace workplace = workplaceService.create(workplaceMapper.fromRequestDto(workplaceRequestDto));
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplace);
        log.info("Controller ==> saveWorkplace() - start: workplace = {}", workplaceResponseDto);
        return workplaceResponseDto;
    }

    @Override
    public WorkplaceResponseDto getWorkplaceById(Integer id) {
        log.info("Controller ==> getWorkplaceById() - start: id = {}", id);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplaceService.getById(id));
        log.info("Controller ==> getWorkplaceById() - end: workplace = {}", workplaceResponseDto);
        return workplaceResponseDto;
    }

    @Override
    public WorkplaceResponseDto refreshWorkspace(Integer id, WorkplaceRequestDto workplaceRequestDto) {
        log.info("Controller ==> refreshWorkspace() - start: id = {}, workspace = {}", id, workplaceRequestDto);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplaceService.updateById(id, workplaceMapper.fromRequestDto(workplaceRequestDto)));
        log.info("Controller ==> refreshWorkspace() - end: id = {}, workspace = {}", id, workplaceResponseDto);
        return workplaceResponseDto;
    }

    @Override
    public void removeAllWorkplaces() {
        log.info("Controller ==> removeAllWorkplaces() - start: ");
        workplaceService.removeAll();
        log.info("Controller ==> removeAllWorkplaces() - end: ");
    }

    @Override
    public WorkplaceResponseDto changeCapacity(Integer id) {
        log.info("Controller ==> changeCapacity() - start: id = {}", id);
        Workplace workplace = workplaceService.updateCapacity(id);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplace);
        log.info("Controller ==> changeCapacity() - end: workplace = {}", workplaceResponseDto);
        return workplaceResponseDto;
    }

    @Override
    public WorkplaceResponseDto checkState(Integer id) {
        log.info("Controller ==> checkState() - start: id = {}", id);
        Workplace workplace = workplaceService.checkState(id);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplace);
        log.info("Controller ==> checkState() - end: workplace = {}", workplaceResponseDto);
        return workplaceResponseDto;
    }

    @Override
    public WorkplaceResponseDto changeOpenness(Integer id) {
        log.info("Controller ==> changeOpenness() - start: id = {}", id);
        Workplace workplace = workplaceService.changeOpenness(id);
        WorkplaceResponseDto workplaceResponseDto = workplaceMapper.toResponseDto(workplace);
        log.info("Controller ==> changeOpenness() - end: workplace = {}", workplaceResponseDto);
        return workplaceResponseDto;
    }
}