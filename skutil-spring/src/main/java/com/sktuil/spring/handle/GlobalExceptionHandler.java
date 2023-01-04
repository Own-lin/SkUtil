package com.sktuil.spring.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * it's a ExceptionHandler demo
 * @author zhan yan
 * @date 2022/12/1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleArrayIndexOutBounds(HttpServletRequest request, HttpServletResponse response,
                                            ArrayIndexOutOfBoundsException e) {
        log.error("array index out conf!");
        return "aryIndexOutOfBounds: " + e.getMessage();
    }


}
