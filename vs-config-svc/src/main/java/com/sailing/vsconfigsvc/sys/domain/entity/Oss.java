package com.sailing.vsconfigsvc.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 上传
 * </p>
 *
 * @author wangxy
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_oss")
@ApiModel(value="SysOss", description="上传")
public class Oss implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Long id;

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
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String file;

    /**
     * 文件类型：图标，文件，文档，图片
     */
    @ApiModelProperty(value = "文件类型：图标，文件，文档，图片")
    private String type;

    /**
     * 文件状态：上传成功，已删除
     */
    @ApiModelProperty(value = "文件状态：上传成功，已删除" ,example = "0")
    private Integer status;

    /**
     * pid
     */
    @ApiModelProperty(value = "pid" ,example = "0")
    private Long pid;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    @TableField( fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;


}
