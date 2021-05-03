package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.Post;
import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.services.model.create.PostCreateDto;
import com.github.progtechteam.socialnetwork.services.model.get.PostGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public interface PostMapper {

    PostGetDto entityToGetDto(Post entity);

    List<PostGetDto> entityToGetDto(List<Post> entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "communityPostOwners", ignore = true)
    @Mapping(target = "userPostOwners", source = "ownerId", qualifiedByName = "getUserOwners")
    @Mapping(target = "author", source = "authorId", qualifiedByName = "getAuthor")
    Post createDtoToEntity(PostCreateDto dto);

    @Named("getAuthor")
    default User getAuthor(Integer authorId) {
        final var author = new User();
        author.setId(authorId);
        return author;
    }

    @Named("getUserOwners")
    default Set<User> getUserOwners(Integer authorId) {
        final var owner = new User();
        owner.setId(authorId);
        return Set.of(owner);
    }

}
