package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.commons.TestType;
import com.github.progtechteam.socialnetwork.data.repository.TestRepository;
import com.github.progtechteam.socialnetwork.services.mapper.TestMapper;
import com.github.progtechteam.socialnetwork.services.model.create.TestCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.TestGetDto;
import com.github.progtechteam.socialnetwork.services.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Service
@Validated
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;
    private final TestRepository testRepository;

    public TestServiceImpl(TestMapper testMapper, TestRepository testRepository) {
        this.testMapper = testMapper;
        this.testRepository = testRepository;
    }

    @Override
    public List<TestGetDto> getAllTests() {
        final var entities = testRepository.findAll();
        return testMapper.toGetDto(entities);
    }

    @Override
    public TestGetDto createTest(@Valid TestCreateDto createDto) {
        final var entityToSave = testMapper.fromCreateDto(createDto);
        if (createDto.getContent().contains("1")) {
            entityToSave.setType(TestType.TYPE_1);
        } else if (createDto.getContent().contains("2")) {
            entityToSave.setType(TestType.TYPE_2);
        } else {
            entityToSave.setType(TestType.OTHER);
        }
        final var savedEntity = testRepository.save(entityToSave);
        return testMapper.toGetDto(savedEntity);
    }
}
