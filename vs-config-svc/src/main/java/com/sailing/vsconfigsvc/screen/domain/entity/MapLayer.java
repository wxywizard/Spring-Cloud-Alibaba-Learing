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
@ApiModel(value="MapLayer", description="地图配置")
public class MapLayer implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 业务代码
     */
    @ApiModelProperty(value = "业务代码")
    private String code;

    /**
     * 业务名称
     */
    @ApiModelProperty(value = "业务名称")
    private String name;

    /**
     * 层级：1-地图实例层，2-业务归类层，3-地图图层
     */
    @ApiModelProperty(value = "层级",example = "1")
    private Integer level;

    /**
     * 图层类别
     */
    @ApiModelProperty(value = "图层类别")
    private String layerType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String description;

    /**
     * 配置项
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "配置项")
    private Object config;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sn;

    /**
     * 页面id
     */
    @ApiModelProperty(value = "页面id")
    private String pageId;

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
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private String appId;


}
