package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.create.TestCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.TestGetDto;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface TestService {

    List<TestGetDto> getAllTests();

    TestGetDto createTest(@Valid TestCreateDto createDto);

}
