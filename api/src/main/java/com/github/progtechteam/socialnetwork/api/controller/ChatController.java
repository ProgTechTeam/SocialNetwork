package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.get.ChatGetDto;
import com.github.progtechteam.socialnetwork.services.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping(
            path = "/{chatId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ChatGetDto getById(@PathVariable int chatId) {
        return chatService.getPrivateChatById(chatId);
    }

    @GetMapping(
            path = "/exists/{userId}"
    )
    public Integer existsForUser(@PathVariable int userId) {
        return chatService.isPrivateChatExistsByUserId(userId);
    }

}
