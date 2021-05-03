package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.data.repository.PostRepository;
import com.github.progtechteam.socialnetwork.services.exception.EntityNotFoundException;
import com.github.progtechteam.socialnetwork.services.mapper.PostMapper;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostGetDto> getByUserId(int userId) {
        log.info("Requested all Posts for User [ID={}]", userId);
        final var posts = postRepository.findAllByUserPostOwnersContains(userId);
        return postMapper.entityToGetDto(posts);
    }

    @Override
    public PostGetDto getById(int postId) {
        log.info("Requested Post [ID={}]", postId);
        final var post = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Post [ID={%d}] not found", postId)));
        return postMapper.entityToGetDto(post);
    }

    @Override
    public PostGetDto create(PostCreateDto dto) {
        log.info("Creating new Post");
        log.debug(dto.toString());
        final var post = postMapper.createDtoToEntity(dto);
        final var savedPost = postRepository.save(post);
        return postMapper.entityToGetDto(savedPost);
    }
}
