package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.services.model.create.PrivateChatCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatRowGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface ChatService {

    List<ChatRowGetDto> getByUserId(int userId);

    ChatGetDto getPrivateChatById(int chatId);

    Integer isPrivateChatExistsByUserId(Integer userId);

    Boolean chatExists(Integer chatId);

    ChatType getChatType(Integer chatId);

    Integer createPrivateChat(Integer userId);

    List<MessageGetDto> getChatMessages(int chatId);

    ChatRowGetDto createPrivate(PrivateChatCreateDto dto);
}
