package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatRowGetDto extends NamedDto {

    private String lastMessageAuthor;
    private Instant lastMessageTime;
    private String lastMessagePayload;

}
