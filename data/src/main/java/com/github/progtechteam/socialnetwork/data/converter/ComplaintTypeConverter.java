package com.github.progtechteam.socialnetwork.data.converter;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.commons.ComplaintType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/**
 * @author Evgenii Puliaev
 */
@Converter(autoApply = true)
public class ComplaintTypeConverter implements AttributeConverter<ComplaintType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ComplaintType attribute) {
        Objects.requireNonNull(
                attribute,
                "Error converting given ChatType to database column: ChatType must not be null!"
        );
        return attribute.getId();
    }

    @Override
    public ComplaintType convertToEntityAttribute(Integer dbData) {
        return ComplaintType.fromId(dbData);
    }
}
