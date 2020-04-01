package com.sailing.vsconfigsvc.utils;

import com.sailing.vsconfigsvc.vo.ResultVo;

/**
 * @author: wangxy
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
          return ResultVo.builder()
                  .code("0")
                  .msg("成功")
                  .data(object)
                  .build();
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(String code,String msg){
          return ResultVo.builder()
                           .code(code)
                           .msg(msg)
                           .build();
    }
}
