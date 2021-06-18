package com.github.progtechteam.socialnetwork.services.model.create;

import lombok.Data;

/**
 * @author Evgenii Puliaev
 */
@Data
public class MessageCreateDto {

    private Integer chatId;
    private String payload;

}
