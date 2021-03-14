package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.create.TestCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.TestGetDto;
import com.github.progtechteam.socialnetwork.services.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<TestGetDto> getAll() {
        return testService.getAllTests();
    }

    @PostMapping
    public TestGetDto create(@RequestBody TestCreateDto dto) {
        return testService.createTest(dto);
    }
}
