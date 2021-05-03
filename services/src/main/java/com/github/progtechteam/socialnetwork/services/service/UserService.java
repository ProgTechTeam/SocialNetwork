package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserGetDto;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface UserService {

    List<UserGetDto> getAll();

    UserGetDto getById(int userId);

    List<UserGetDto> getSubscribers(int userId);

    List<UserGetDto> getSubscriptions(int userId);

    List<UserGetDto> getFriends(int userId);

    CurrentUser create(UserCreateDto dto);

    void subscribe(int userId);

    void unsubscribe(int userId);
}
