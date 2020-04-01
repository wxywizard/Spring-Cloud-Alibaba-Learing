package com.sailing.vsconfigsvc.screen.domain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
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
@ApiModel(value="Component", description="组件")
public class Component implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 组件id
     */
    @ApiModelProperty(value = "组件id")
    private String id;

    /**
     * 组件名称
     */
    @ApiModelProperty(value = "组件名称")
    private String name;

    /**
     * 组件属性
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "组件属性")
    private Object props;

    /**
     * 组件样式
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "组件样式")
    private Object style;

    /**
     * 事件
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "事件")
    private Object reacts;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;

    /**
     * 页面id
     */
    @ApiModelProperty(value = "页面id")
    private String pageId;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    private String group;

    /**
     * 有无容器
     */
    @ApiModelProperty(value = "有无容器")
    private String container;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String sn;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private String appId;


}
