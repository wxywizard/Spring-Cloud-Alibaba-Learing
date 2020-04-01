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
@ApiModel(value="Template", description="模板配置")
public class Template implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    private String name;

    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String sketch;

    /**
     * 配置项
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "配置项")
    private Object config;

    /**
     * 比例
     */
    @ApiModelProperty(value = "比例")
    private String proportion;

    /**
     * 分辨率
     */
    @ApiModelProperty(value = "分辨率")
    private String resolution;

    /**
     * 行业
     */
    @ApiModelProperty(value = "行业")
    private String industry;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createAt;


}
