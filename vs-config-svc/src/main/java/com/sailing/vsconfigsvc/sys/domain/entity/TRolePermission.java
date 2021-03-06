package com.sailing.vsconfigsvc.sys.domain.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangxy
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TRolePermission", description="角色权限")
public class TRolePermission implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private String appId;


}
