package com.guanghui.amen.exception;

import com.guanghui.amen.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(ServiiceException.class)
    @ResponseBody
    public Result handle(ServiiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }


}
