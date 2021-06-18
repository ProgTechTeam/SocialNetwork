package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.commons.Url;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;
import com.github.progtechteam.socialnetwork.services.service.UserService;
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
@RequestMapping(Url.PUBLIC)
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CurrentUser registerUser(@RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

}
