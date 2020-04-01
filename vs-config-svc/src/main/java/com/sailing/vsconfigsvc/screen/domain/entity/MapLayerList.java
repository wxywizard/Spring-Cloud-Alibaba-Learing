package com.sailing.vsconfigsvc.screen.domain.entity;

import java.io.Serializable;

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
@ApiModel(value="MapLayerList", description="地图数据绑定")
public class MapLayerList implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 图层id
     */
    @ApiModelProperty(value = "图层id")
    private String layerId;

    /**
     * 数据源id
     */
    @ApiModelProperty(value = "数据源id")
    private String dataId;

    /**
     * 数据源名称
     */
    @ApiModelProperty(value = "数据源名称")
    private String dataName;

    /**
     * 数据类型
1.静态数据
2.动态数据
     */
    @ApiModelProperty(value = "数据类型",example = "1")
    private String type;

    /**
     * 静态数据
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "静态数据")
    private Object dataset;


}
