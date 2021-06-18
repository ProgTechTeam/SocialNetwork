package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.create.MessageCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;

/**
 * @author Evgenii Puliaev
 */
public interface MessageService {

    MessageGetDto createMessage(MessageCreateDto dto);
}
