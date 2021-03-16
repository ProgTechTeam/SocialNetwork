package com.github.progtechteam.socialnetwork.data.converter;

import com.github.progtechteam.socialnetwork.commons.TestType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Evgenii Puliaev
 */
@Converter(autoApply = true)
public class TestConverter implements AttributeConverter<TestType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TestType attribute) {
        return attribute == null ? TestType.OTHER.getId() : attribute.getId();
    }

    @Override
    public TestType convertToEntityAttribute(Integer dbData) {
        return TestType.fromId(dbData);
    }
}