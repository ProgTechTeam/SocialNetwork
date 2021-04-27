package com.github.progtechteam.socialnetwork.data.converter;

import com.github.progtechteam.socialnetwork.commons.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Evgenii Puliaev
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        return attribute == null ? Role.USER.getId() : attribute.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        return Role.fromId(dbData);
    }
}
