package com.sailing.vsconfigsvc.common.exception;

import com.sailing.vsconfigsvc.enums.ResultEnum;
import lombok.Getter;

/**
 * @author: wangxy
 */
@Getter
public class VsConfigException extends RuntimeException {

    private String code;

    public VsConfigException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public VsConfigException(String message, String code) {
        super(message);
        this.code = code;
    }
}
