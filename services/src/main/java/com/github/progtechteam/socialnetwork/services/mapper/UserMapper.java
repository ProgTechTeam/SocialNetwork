package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserGetDto;
import com.github.progtechteam.socialnetwork.services.model.get.UserProfileGetDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private CurrentUser currentUser;

    @Mapping(target = "subscribed", source = "entity", qualifiedByName = "getSubscribed")
    @Mapping(target = "friends", source = "friends", qualifiedByName = "getSize")
    @Mapping(target = "subscribers", source = "subscribers", qualifiedByName = "getSize")
    @Mapping(target = "subscriptions", source = "subscriptionsOnUsers", qualifiedByName = "getSize")
    @Mapping(target = "email", source = "account.email")
    public abstract UserProfileGetDto entityToProfileGetDto(User entity);

    @Named("entityToGetDto")
    @Mapping(target = "email", source = "account.email")
    public abstract UserGetDto entityToGetDto(User entity);

    @IterableMapping(qualifiedByName = "entityToGetDto")
    public abstract List<UserGetDto> entityToGetDto(Collection<User> entities);

    @Named("entityToNamedDto")
    @Mapping(target = "name", source = "fullName")
    public abstract NamedDto entityToNamedDto(User entity);

    @IterableMapping(qualifiedByName = "entityToNamedDto")
    public abstract List<NamedDto> entityToNamedDto(Collection<User> entities);

    @Named("getSize")
    public Integer getSize(Set<User> collection) {
        return collection.size();
    }

    @Named("getSubscribed")
    public Boolean getSubscribed(User entity) {
        return entity.getSubscribers().stream().anyMatch(u -> u.getId().equals(currentUser.getId()))
                || entity.getFriends().stream().anyMatch(u -> u.getId().equals(currentUser.getId()));
    }

}
