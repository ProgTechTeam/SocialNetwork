package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.data.entity.chat.PrivateChat;
import com.github.progtechteam.socialnetwork.data.repository.ChatRepository;
import com.github.progtechteam.socialnetwork.data.repository.PrivateChatRepository;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.exception.EntityNotFoundException;
import com.github.progtechteam.socialnetwork.services.mapper.ChatMapper;
import com.github.progtechteam.socialnetwork.services.mapper.MessageMapper;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.PrivateChatCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatRowGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;
import com.github.progtechteam.socialnetwork.services.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final PrivateChatRepository privateChatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;
    private final CurrentUser currentUser;

    @Override
    public List<ChatRowGetDto> getByUserId(int userId) {
        log.info("Requested all Chats for User [ID={}]", userId);
        final var privateChats = privateChatRepository.findChatsByParticipantId(userId);
        return chatMapper.privateChatToRowGetDto(privateChats)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public ChatGetDto getPrivateChatById(int chatId) {
        log.info("Requested Chat [ID={}]", chatId);
        final var chat = privateChatRepository
                .findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Chat [ID={%d}] not found", chatId)));
        return chatMapper.privateChatToGetDto(chat);
    }

    @Override
    public Integer isPrivateChatExistsByUserId(Integer userId) {
        log.info("Requested Existence of Chat for User [ID={}]", userId);
        return privateChatRepository.findChatIdByParticipantsId(userId, currentUser.getId());
    }

    @Override
    public Boolean chatExists(Integer chatId) {
        log.info("Checking for existence Chat [ID={}]", chatId);
        return chatRepository.existsById(chatId);
    }

    @Override
    public ChatType getChatType(Integer chatId) {
        log.info("Requested ChatType for Chat [ID={}]", chatId);
        return chatRepository
                .getChatTypeById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Chat [ID={%d}] not found", chatId)));
    }

    @Transactional
    @Override
    public Integer createPrivateChat(Integer userId) {
        log.info("Creating new PrivateChat");
        log.debug("Creator [ID={}], SecondParticipant [ID={}]", currentUser.getId(), userId);

        final var secondParticipant = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User [ID={%d}] not found", userId)));

        final var chat = new PrivateChat();
        chat.setChatType(ChatType.PRIVATE_CHAT);
        chat.setFirstParticipant(userRepository.getOne(currentUser.getId()));
        chat.setSecondParticipant(secondParticipant);
        final var savedChat = privateChatRepository.save(chat);
        return savedChat.getId();
    }

    @Override
    public List<MessageGetDto> getChatMessages(int chatId) {
        log.info("Requested Messages for Chat [ID={}]", chatId);
        final var chat = chatRepository
                .findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Chat [ID={%d}] not found", chatId)));

        return messageMapper.entityToGetDto(chat.getMessages());
    }

    @Override
    public ChatRowGetDto createPrivate(PrivateChatCreateDto dto) {
        log.info("Creating Private Chat with User [ID={}]", dto.getUserId());
        final var chat = new PrivateChat();
        chat.setChatType(ChatType.PRIVATE_CHAT);
        chat.setFirstParticipant(userRepository.getOne(dto.getUserId()));
        chat.setSecondParticipant(userRepository.getOne(currentUser.getId()));
        final var savedChat = chatRepository.save(chat);
        return chatMapper.privateChatToRowGetDto(savedChat);
    }
}
