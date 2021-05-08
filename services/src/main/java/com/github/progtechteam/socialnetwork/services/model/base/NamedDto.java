package com.github.progtechteam.socialnetwork.services.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NamedDto extends BaseDto {

    private String name;

}
