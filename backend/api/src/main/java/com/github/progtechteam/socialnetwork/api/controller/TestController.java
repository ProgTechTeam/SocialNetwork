package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.create.TestCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.TestGetDto;
import com.github.progtechteam.socialnetwork.services.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Tag(name = "TestController", description = "Controller to manage test instances")
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(summary = "get all tests", description = "Fetches all test instances in system")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestGetDto> getAll() {
        return testService.getAllTests();
    }

    @Operation(summary = "create new test", description = "Creates new test instance in system")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TestGetDto create(@RequestBody TestCreateDto dto) {
        return testService.createTest(dto);
    }
}
