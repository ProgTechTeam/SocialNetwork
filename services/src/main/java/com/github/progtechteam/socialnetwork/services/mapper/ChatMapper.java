package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.chat.PrivateChat;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.get.ChatGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatRowGetDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public abstract class ChatMapper {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserMapper userMapper;

    @Named("privateChatToRowGetDto")
    public ChatRowGetDto privateChatToRowGetDto(PrivateChat entity) {
        final var row = new ChatRowGetDto();
        row.setId(entity.getId());

        final var rowName = entity.getFirstParticipant().getId().equals(currentUser.getId())
                ? entity.getSecondParticipant().getFullName()
                : entity.getFirstParticipant().getFullName();
        row.setName(rowName);

        if (entity.getMessages().isEmpty()) {
            row.setLastMessageTime(Instant.EPOCH);
            return row;
        }
        final var lastMessage = entity.getMessages().get(entity.getMessages().size() - 1);
        row.setLastMessageAuthor(lastMessage.getAuthor().getFullName());
        row.setLastMessagePayload(lastMessage.getPayload());
        row.setLastMessageTime(lastMessage.getCreatedAt());

        return row;
    }

    @IterableMapping(qualifiedByName = "privateChatToRowGetDto")
    public abstract List<ChatRowGetDto> privateChatToRowGetDto(List<PrivateChat> entities);

    public ChatGetDto privateChatToGetDto(PrivateChat entity) {
        final var dto = new ChatGetDto();
        dto.setId(entity.getId());

        final var name = entity.getFirstParticipant().getId().equals(currentUser.getId())
                ? entity.getSecondParticipant().getFullName()
                : entity.getFirstParticipant().getFullName();
        dto.setName(name);

        dto.setChatType(entity.getChatType());
        dto.setParticipants(userMapper.entityToNamedDto(List.of(
                entity.getFirstParticipant(),
                entity.getSecondParticipant()
        )));

        return dto;
    }

}
