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
@ApiModel(value="ComponentList", description="组件绑定的数据")
public class ComponentList implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 组件id
     */
    @ApiModelProperty(value = "组件id")
    private String componentId;

    /**
     * 绑定数据源id
     */
    @ApiModelProperty(value = "绑定数据源id")
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
