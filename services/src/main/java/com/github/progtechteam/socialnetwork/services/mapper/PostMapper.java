package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.Post;
import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class}
)
public abstract class PostMapper {

    @Autowired
    private UserRepository userRepository;

    public abstract PostGetDto entityToGetDto(Post entity);

    public abstract List<PostGetDto> entityToGetDto(List<Post> entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userPostOwners", ignore = true)
    @Mapping(target = "communityPostOwners", ignore = true)
    @Mapping(target = "author", source = "authorId", qualifiedByName = "getAuthor")
    public abstract Post createDtoToEntity(PostCreateDto dto);

    @Named("getAuthor")
    public User getAuthor(Integer authorId) {
        return userRepository.getOne(authorId);
    }

}
