package com.github.progtechteam.socialnetwork.services.mapper;

import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.services.model.get.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "role", source = "account.role")
    UserGetDto entityToGetDto(User entity);

    List<UserGetDto> entityToGetDto(Collection<User> entity);

}
