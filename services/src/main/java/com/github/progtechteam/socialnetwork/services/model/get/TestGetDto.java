package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.commons.TestType;
import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TestGetDto extends BaseDto {

    private String content;
    private TestType type;

}
