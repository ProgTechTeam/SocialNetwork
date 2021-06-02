package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface UserService {

    UserProfileGetDto getById(int userId);

    List<NamedDto> getAll();

    List<NamedDto> getSubscribers(int userId);

    List<NamedDto> getSubscriptions(int userId);

    List<NamedDto> getFriends(int userId);

    CurrentUser create(UserCreateDto dto);

    UserProfileGetDto subscribe(int userId);

    UserProfileGetDto unsubscribe(int userId);

    PostGetDto like(int postId);

    PostGetDto cancelLike(int postId);
}
