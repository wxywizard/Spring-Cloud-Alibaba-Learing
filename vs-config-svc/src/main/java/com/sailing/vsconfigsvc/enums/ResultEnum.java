package com.sailing.vsconfigsvc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
