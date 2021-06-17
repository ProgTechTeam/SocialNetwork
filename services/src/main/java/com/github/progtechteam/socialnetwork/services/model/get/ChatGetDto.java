package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.services.model.base.NamedDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatGetDto extends NamedDto {

    private ChatType chatType;
    private List<NamedDto> participants;

}
