package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostGetDto extends BaseDto {

    private String payload;
    private NamedDto author;
    private Instant createdAt;
    private List<BaseDto> likedUsers;

}
