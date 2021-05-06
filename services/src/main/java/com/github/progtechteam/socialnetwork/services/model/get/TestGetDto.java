package com.github.progtechteam.socialnetwork.services.model.get;

import com.github.progtechteam.socialnetwork.commons.TestType;
import com.github.progtechteam.socialnetwork.services.model.base.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "Contains information about test instance")
public class TestGetDto extends BaseDto {

    private String content;
    private TestType type;

}
