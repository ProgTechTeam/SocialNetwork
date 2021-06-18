package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import com.github.progtechteam.socialnetwork.services.model.get.ChatRowGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;
import com.github.progtechteam.socialnetwork.services.service.ChatService;
import com.github.progtechteam.socialnetwork.services.service.PostService;
import com.github.progtechteam.socialnetwork.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    private final PostService postService;
    private final ChatService chatService;

    @GetMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserProfileGetDto getById(@PathVariable int userId) {
        return userService.getById(userId);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<NamedDto> getAll() {
        return userService.getAll();
    }

    @GetMapping(
            path = "/{userId}/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<NamedDto> getSubscribers(@PathVariable int userId) {
        return userService.getSubscribers(userId);
    }

    @GetMapping(
            path = "/{userId}/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<NamedDto> getSubscriptions(@PathVariable int userId) {
        return userService.getSubscriptions(userId);
    }

    @GetMapping(
            path = "/{userId}/friends",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<NamedDto> getFriends(@PathVariable int userId) {
        return userService.getFriends(userId);
    }

    @GetMapping(
            path = "/{userId}/news",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PostGetDto> getNews(@PathVariable int userId) {
        return postService.getNewsByUserId(userId);
    }

    @GetMapping(
            path = "/{userId}/posts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PostGetDto> getPosts(@PathVariable int userId) {
        return postService.getByUserId(userId);
    }

    @GetMapping(
            path = "/{userId}/chats",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ChatRowGetDto> getChats(@PathVariable int userId) {
        return chatService.getByUserId(userId);
    }

    @PutMapping(
            path = "/{userId}/subscribe",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserProfileGetDto subscribe(@PathVariable int userId) {
        return userService.subscribe(userId);
    }

    @PutMapping(
            path = "/{userId}/unsubscribe",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserProfileGetDto unsubscribe(@PathVariable int userId) {
        return userService.unsubscribe(userId);
    }

    @PutMapping(path = "/{postId}/like")
    public PostGetDto like(@PathVariable int postId) {
        return userService.like(postId);
    }

    @PutMapping(path = "/{postId}/cancel-like")
    public PostGetDto cancelLike(@PathVariable int postId) {
        return userService.cancelLike(postId);
    }
}
