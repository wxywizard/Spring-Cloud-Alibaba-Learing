package com.sailing.vsconfigsvc.screen.domain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Application", description="应用")
public class Application implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    private String name;

    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String sketch;

    /**
     * 发布状态
     */
    @ApiModelProperty(value = "发布状态")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;


}
