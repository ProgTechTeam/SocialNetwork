package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "friends", source = "friends", qualifiedByName = "getSize")
    @Mapping(target = "subscribers", source = "subscribers", qualifiedByName = "getSize")
    @Mapping(target = "subscriptions", source = "subscriptionsOnUsers", qualifiedByName = "getSize")
    @Mapping(target = "email", source = "account.email")
    UserProfileGetDto entityToProfileGetDto(User entity);

    @Named("entityToGetDto")
    @Mapping(target = "email", source = "account.email")
    UserGetDto entityToGetDto(User entity);

    @IterableMapping(qualifiedByName = "entityToGetDto")
    List<UserGetDto> entityToGetDto(Collection<User> entities);

    @Named("entityToNamedDto")
    @Mapping(target = "name", source = "fullName")
    NamedDto entityToNamedDto(User entity);

    @IterableMapping(qualifiedByName = "entityToNamedDto")
    List<NamedDto> entityToNamedDto(Collection<User> entities);

    @Named("getSize")
    default Integer getSize(Set<User> collection) {
        return collection.size();
    }

}
