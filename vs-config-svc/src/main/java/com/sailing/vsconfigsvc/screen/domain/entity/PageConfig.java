package com.sailing.vsconfigsvc.screen.domain.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
@ApiModel(value="PageConfig", description="页面配置")
public class PageConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 页面样式
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "页面样式")
    private Object style;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createAt;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateAt;

    /**
     * 页面配置项
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "页面配置项")
    private Object page;

    /**
     * 页面形态
     */
    @ApiModelProperty(value = "页面形态")
    private String status;

    /**
     * 模版id
     */
    @ApiModelProperty(value = "模版id")
    @TableField("templateId")
    private String templateId;

    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String sketch;

    /**
     * 区分page或者model
     */
    @ApiModelProperty(value = "区分page或者model",example = "page")
    private String type;

    /**
     * 配置信息
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "配置信息")
    private Object config;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private String appId;


}
