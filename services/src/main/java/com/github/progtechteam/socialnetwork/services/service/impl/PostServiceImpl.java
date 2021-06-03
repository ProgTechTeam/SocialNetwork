package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.commons.ComplaintType;
import com.github.progtechteam.socialnetwork.data.entity.Complaint;
import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.data.repository.ComplaintRepository;
import com.github.progtechteam.socialnetwork.data.repository.PostRepository;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.exception.EntityNotFoundException;
import com.github.progtechteam.socialnetwork.services.mapper.PostMapper;
import com.github.progtechteam.socialnetwork.services.mapper.UserMapper;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private static final String USER_NOT_FOUND_MESSAGE = "User [ID={%d}] not found";
    private static final String POST_NOT_FOUND_MESSAGE = "Post [ID={%d}] not found";

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final CurrentUser currentUser;

    @Override
    public List<PostGetDto> getByUserId(int userId) {
        log.info("Requested all Posts for User [ID={}]", userId);
        final var posts = postRepository.findAllByUserPostOwnersContains(userId);
        return postMapper.entityToGetDto(posts);
    }

    @Override
    public List<BaseDto> getLikedUsers(int postId) {
        log.info("Requested all liked users for Post [ID={}]", postId);
        final var users = userRepository.findAllLikedUsersByPostId(postId);
        return userMapper.entityToBaseDto(users);
    }

    @Override
    public List<PostGetDto> getNewsByUserId(int userId) {
        log.info("Requested news for User [ID={}]", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        userId
                )));
        List<PostGetDto> posts = new ArrayList<>();
        for (User subscriber : user.getSubscriptionsOnUsers()) {
            posts.addAll(getByUserId(subscriber.getId()));
        }
        for (User friend : user.getFriends()) {
            posts.addAll(getByUserId(friend.getId()));
        }
        return posts;
    }

    @Override
    public PostGetDto getById(int postId) {
        log.info("Requested Post [ID={}]", postId);
        final var post = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Post [ID={%d}] not found", postId)));
        return postMapper.entityToGetDto(post);
    }

    @Transactional
    @Override
    public PostGetDto create(PostCreateDto dto) {
        log.info("Creating new Post");
        log.debug(dto.toString());

        dto.setAuthorId(currentUser.getId());
        final var post = postMapper.createDtoToEntity(dto);
        final var savedPost = postRepository.save(post);

        final var user = userRepository
                .findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User [ID={%d}] not found", dto.getOwnerId())));
        user.addPost(savedPost);

        return postMapper.entityToGetDto(savedPost);
    }

    @Override
    public PostGetDto complaint(int postId, int complaintTypeId) {
        log.info("User [ID={}] requested create complaints to Post [ID={}]", currentUser.getId(), postId);
        final var currUser = userRepository.getOne(currentUser.getId());
        final var postToComplaint = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, postId)));
        Complaint complaint = new Complaint();
        complaint.setUser(currUser);
        complaint.setPost(postToComplaint);
        complaint.setComplaintType(ComplaintType.fromId(complaintTypeId));
        complaintRepository.save(complaint);
        return postMapper.entityToGetDto(postToComplaint);
    }

    @Override
    public List<ComplaintType> getComplaintsTypes() {
        log.info("User [ID={}] requested all complaints types", currentUser.getId());
        return Arrays.asList(ComplaintType.values());
    }
}
