package com.example.demo.exception;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/15 15:18
 **/

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
