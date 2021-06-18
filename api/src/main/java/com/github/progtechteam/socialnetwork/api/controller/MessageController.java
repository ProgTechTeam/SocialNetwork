package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.create.MessageCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.MessageGetDto;
import com.github.progtechteam.socialnetwork.services.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageGetDto create(@RequestBody MessageCreateDto dto) {
        return messageService.createMessage(dto);
    }

}
