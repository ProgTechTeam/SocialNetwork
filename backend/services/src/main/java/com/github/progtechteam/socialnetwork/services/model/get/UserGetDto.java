package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserGetDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String email;

}
