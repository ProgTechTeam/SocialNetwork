package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.commons.Role;
import com.github.progtechteam.socialnetwork.data.entity.Account;
import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.data.repository.AccountRepository;
import com.github.progtechteam.socialnetwork.data.repository.PostRepository;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.exception.EntityNotFoundException;
import com.github.progtechteam.socialnetwork.services.exception.UserAlreadyExistsException;
import com.github.progtechteam.socialnetwork.services.mapper.PostMapper;
import com.github.progtechteam.socialnetwork.services.mapper.UserMapper;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;
import com.github.progtechteam.socialnetwork.services.service.AuthenticationService;
import com.github.progtechteam.socialnetwork.services.service.PasswordEncoderService;
import com.github.progtechteam.socialnetwork.services.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User [ID={%d}] not found";
    private static final String POST_NOT_FOUND_MESSAGE = "Post [ID={%d}] not found";

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final CurrentUser currentUser;

    @Override
    public UserProfileGetDto getById(int userId) {
        log.info("Requested User [ID={}]", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        userId
                )));
        return userMapper.entityToProfileGetDto(user);
    }

    @Override
    public List<NamedDto> getAll() {
        log.info("Requested list of all users");
        final var users = userRepository.findAll();
        return userMapper.entityToNamedDto(users);
    }

    @Override
    public List<NamedDto> getSubscribers(int userId) {
        log.info("Requested subscribers of User [ID={}]", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        userId
                )));
        return userMapper.entityToNamedDto(user.getSubscribers());
    }

    @Override
    public List<NamedDto> getSubscriptions(int userId) {
        log.info("Requested subscriptions of User [ID={}]", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        userId
                )));
        return userMapper.entityToNamedDto(user.getSubscriptionsOnUsers());
    }

    @Override
    public List<NamedDto> getFriends(int userId) {
        log.info("Requested friends of User [ID={}]", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        userId
                )));
        return userMapper.entityToNamedDto(user.getFriends());
    }

    @Override
    public CurrentUser create(UserCreateDto dto) {
        final var alreadyExists = accountRepository.findByEmail(dto.getEmail()).isPresent();
        if (alreadyExists) {
            throw new UserAlreadyExistsException(
                    String.format("Email [{%s}] already registered in system", dto.getEmail())
            );
        }

        final var newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        final var savedUser = userRepository.saveAndFlush(newUser);

        final var newAccount = new Account();
        newAccount.setEmail(dto.getEmail());
        newAccount.setRole(Role.USER);
        newAccount.setPassword(passwordEncoderService.encode(dto.getPassword()));
        newAccount.setAccountOwner(savedUser);
        accountRepository.saveAndFlush(newAccount);

        final var account = accountRepository
                .findById(savedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        USER_NOT_FOUND_MESSAGE,
                        savedUser.getId()
                )));
        log.info("User [email={}] was successfully created", account.getEmail());
        return authenticationService.authenticate(dto.getEmail(), dto.getPassword());
    }

    @Transactional
    @Override
    public UserProfileGetDto subscribe(int userId) {
        log.info("User [ID={}] requested subscription to User [ID={}]", currentUser.getId(), userId);
        final var currUser = userRepository.getOne(currentUser.getId());
        final var userToSubscribe = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));
        currUser.subscribeToUser(userToSubscribe);
        return userMapper.entityToProfileGetDto(userToSubscribe);
    }

    @Transactional
    @Override
    public UserProfileGetDto unsubscribe(int userId) {
        log.info("User [ID={}] requested cancellation of subscription to User [ID={}]", currentUser.getId(), userId);
        final var currUser = userRepository.getOne(currentUser.getId());
        final var userToUnsubscribe = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));
        currUser.unsubscribeFromUser(userToUnsubscribe);
        return userMapper.entityToProfileGetDto(userToUnsubscribe);
    }

    @Override
    public PostGetDto like(int postId) {
        log.info("User [ID={}] requested like to Post [ID={}]", currentUser.getId(), postId);
        final var currUser = userRepository.getOne(currentUser.getId());
        final var postToLike = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, postId)));
        currUser.like(postToLike);
        postRepository.save(postToLike);
        return postMapper.entityToGetDto(postToLike);
    }

    @Override
    public PostGetDto cancelLike(int postId) {
        log.info("User [ID={}] requested like cancellation to Post [ID={}]", currentUser.getId(), postId);
        final var currUser = userRepository.getOne(currentUser.getId());
        final var postToCancelLike = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(POST_NOT_FOUND_MESSAGE, postId)));
        currUser.cancelLike(postToCancelLike);
        postRepository.save(postToCancelLike);
        return postMapper.entityToGetDto(postToCancelLike);
    }
}
