package com.github.progtechteam.socialnetwork.services.model.create;

import lombok.Data;

/**
 * @author Evgenii Puliaev
 */
@Data
public class PostCreateDto {

    private String payload;
    private Integer authorId;
    private Integer ownerId;

}
