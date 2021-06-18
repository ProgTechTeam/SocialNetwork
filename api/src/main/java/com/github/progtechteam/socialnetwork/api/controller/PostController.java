package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(
            path = "/{postId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PostGetDto getById(@PathVariable int postId) {
        return postService.getById(postId);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PostGetDto create(@RequestBody PostCreateDto dto) {
        return postService.create(dto);
    }
}
