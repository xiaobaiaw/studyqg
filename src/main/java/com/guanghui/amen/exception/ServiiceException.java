package com.guanghui.amen.exception;

import lombok.Getter;

@Getter
public class ServiiceException extends RuntimeException {

    private String code;

    public ServiiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
