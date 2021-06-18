package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.data.entity.Message;
import com.github.progtechteam.socialnetwork.data.repository.ChatRepository;
import com.github.progtechteam.socialnetwork.data.repository.MessageRepository;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.mapper.MessageMapper;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.MessageCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;
import com.github.progtechteam.socialnetwork.services.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;
    private final CurrentUser currentUser;

    @Override
    public MessageGetDto createMessage(MessageCreateDto dto) {
        log.info("Creating Message for Chat [ID={}]", dto.getChatId());
        log.debug(dto.toString());

        final var message = new Message();
        message.setAuthor(userRepository.getOne(currentUser.getId()));
        message.setChat(chatRepository.getOne(dto.getChatId()));
        message.setPayload(dto.getPayload());
        final var savedMessage = messageRepository.save(message);
        return messageMapper.entityToGetDto(savedMessage);
    }
}
