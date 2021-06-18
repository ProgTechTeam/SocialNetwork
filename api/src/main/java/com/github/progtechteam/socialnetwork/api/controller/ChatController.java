package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.create.PrivateChatCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatRowGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;
import com.github.progtechteam.socialnetwork.services.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(
            path = "/{chatId}/messages"
    )
    public List<MessageGetDto> getMessages(@PathVariable int chatId) {
        return chatService.getChatMessages(chatId);
    }

    @PostMapping(
            path = "/private",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ChatRowGetDto create(@RequestBody PrivateChatCreateDto dto) {
        return chatService.createPrivate(dto);
    }

}
