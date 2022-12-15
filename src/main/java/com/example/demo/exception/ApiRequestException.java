package com.example.demo.exception;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/15 15:46
 **/
public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
