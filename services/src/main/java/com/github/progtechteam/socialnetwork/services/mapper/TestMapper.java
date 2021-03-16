package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.Test;
import com.github.progtechteam.socialnetwork.services.model.create.TestCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.TestGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public interface TestMapper {

    TestGetDto toGetDto(Test entity);

    List<TestGetDto> toGetDto(List<Test> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    Test fromCreateDto(TestCreateDto dto);

}
