package com.example.demowithtests.web.workplace;

import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface WorkplaceController {

    @Operation(summary = "This is endpoint to add a new workplace.", description = "Create request to add a new workplace.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new workplace is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found."),
            @ApiResponse(responseCode = "409", description = "Workplace already exists")})
    @PostMapping("/workplace")
    @ResponseStatus(HttpStatus.CREATED)
    WorkplaceResponseDto saveWorkplace(@RequestBody WorkplaceRequestDto workplaceRequestDto);

    @Operation(summary = "This is endpoint to find the workplace.", description = "Create request to get workplace by ID.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data successfully get."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified workplace request not found.")})
    @GetMapping(value = "/workplace/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto getWorkplaceById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint to refresh workspace by ID & new body.", description = "Create request to refresh workspace by ID and body.", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. workspace was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. workspace does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/workspace/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto refreshWorkspace(@PathVariable("id") Integer id, @RequestBody WorkplaceRequestDto workplaceRequestDto);

    @Operation(summary = "This is endpoint to remove all workplaces.", description = "Create request to remove workplaces.", tags = {"Workplace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESS. All workplaces were deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. DB or Table don't exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @DeleteMapping("/workplaces")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAllWorkplaces();

    @Operation(summary = "This is endpoint to change workplace's capacity by ID.", description = "Create request to refresh workspace by ID.", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Workspace was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Workspace does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/workspace/capacity/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto changeCapacity(@PathVariable("id") Integer id);

    @Operation(summary = "This is endpoint to change workplace's state by ID.", description = "Create request to refresh workspace by ID.", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Workspace was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Workspace does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/workspace/state/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto checkState(@PathVariable("id") Integer id);

    @Operation(summary = "This is endpoint to change workplace's openness state by ID.", description = "Create request to refresh workspace by ID.", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS. Workspace was refreshed."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Workspace does not exist."),
            @ApiResponse(responseCode = "500", description = "Server error.")})
    @PutMapping("/workspace/openness/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkplaceResponseDto changeOpenness(@PathVariable("id") Integer id);


}