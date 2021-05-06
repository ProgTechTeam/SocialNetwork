package com.github.progtechteam.socialnetwork.services.model.get;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfileGetDto extends UserGetDto {

    private Integer friends;
    private Integer subscribers;
    private Integer subscriptions;
    private Boolean subscribed;

}
