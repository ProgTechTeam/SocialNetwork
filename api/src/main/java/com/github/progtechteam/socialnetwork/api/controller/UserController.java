package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.get.UserGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;
import com.github.progtechteam.socialnetwork.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{userId}")
    public UserProfileGetDto getById(@PathVariable int userId) {
        return userService.getById(userId);
    }

    @GetMapping
    public List<UserGetDto> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = "/{userId}/subscribers")
    public List<UserGetDto> getSubscribers(@PathVariable int userId) {
        return userService.getSubscribers(userId);
    }

    @GetMapping(path = "/{userId}/subscriptions")
    public List<UserGetDto> getSubscriptions(@PathVariable int userId) {
        return userService.getSubscriptions(userId);
    }

    @GetMapping(path = "/{userId}/friends")
    public List<UserGetDto> getFriends(@PathVariable int userId) {
        return userService.getFriends(userId);
    }

    @PutMapping(path = "/{userId}/subscribe")
    public void subscribe(@PathVariable int userId) {
        userService.subscribe(userId);
    }

    @PutMapping(path = "/{userId}/unsubscribe")
    public void unsubscribe(@PathVariable int userId) {
        userService.unsubscribe(userId);
    }

}
