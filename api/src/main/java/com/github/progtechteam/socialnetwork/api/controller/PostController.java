package com.github.progtechteam.socialnetwork.api.controller;

import com.github.progtechteam.socialnetwork.commons.ComplaintType;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(path = "/{postId}")
    public PostGetDto getById(@PathVariable int postId) {
        return postService.getById(postId);
    }

    @GetMapping(path = "/complaints-types")
    public List<ComplaintType> getComplaintsTypes() {
        return postService.getComplaintsTypes();
    }

    @PostMapping
    public PostGetDto create(@RequestBody PostCreateDto dto) {
        return postService.create(dto);
    }

    @PutMapping(path = "/{postId}/complaint/{complaintTypeId}")
    public PostGetDto complaint(@PathVariable int postId, @PathVariable int complaintTypeId) {
        return postService.complaint(postId, complaintTypeId);
    }


}
