package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface PostService {

    List<PostGetDto> getByUserId(int userId);

    List<BaseDto> getLikedUsers(int postId);

    List<PostGetDto> getNewsByUserId(int userId);

    PostGetDto getById(int postId);

    PostGetDto create(PostCreateDto dto);

}
