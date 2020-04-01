package com.sailing.vsconfigsvc.common.handler;

import com.sailing.vsconfigsvc.common.exception.VsConfigException;
import com.sailing.vsconfigsvc.utils.ResultVoUtil;
import com.sailing.vsconfigsvc.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wangxy
 */
@ControllerAdvice
public class VsConfigExceptionHandler {

    @ExceptionHandler(value = VsConfigException.class)
    @ResponseBody
    public ResultVo handlerVsConfigException(VsConfigException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }
}
