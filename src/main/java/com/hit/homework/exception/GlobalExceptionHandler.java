package com.hit.homework.exception;

import com.hit.homework.conmon.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptionDisplay(Exception e){
        e.printStackTrace();
        return Result.error("程序错误");
    }
}
