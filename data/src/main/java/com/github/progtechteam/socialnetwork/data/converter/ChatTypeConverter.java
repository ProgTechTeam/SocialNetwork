package com.github.progtechteam.socialnetwork.data.converter;

import com.github.progtechteam.socialnetwork.commons.ChatType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/**
 * @author Evgenii Puliaev
 */
@Converter(autoApply = true)
public class ChatTypeConverter implements AttributeConverter<ChatType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChatType attribute) {
        Objects.requireNonNull(
                attribute,
                "Error converting given ChatType to database column: ChatType must not be null!"
        );
        return attribute.getId();
    }

    @Override
    public ChatType convertToEntityAttribute(Integer dbData) {
        return ChatType.fromId(dbData);
    }
}
