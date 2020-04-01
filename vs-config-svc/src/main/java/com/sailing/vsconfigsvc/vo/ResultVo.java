package com.sailing.vsconfigsvc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wangxy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -8322598757980436268L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 信息提示
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
