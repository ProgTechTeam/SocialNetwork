package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.Message;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class}
)
public interface MessageMapper {

    @Mapping(target = "author", source = "author", qualifiedByName = "entityToNamedDto")
    MessageGetDto entityToGetDto(Message entity);

    List<MessageGetDto> entityToGetDto(List<Message> entities);

}
