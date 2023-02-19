package com.example.demo.customer.entity;

import lombok.Data;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2023/1/9 11:23
 **/
@Data
public class ClassicCustomersRequest extends Customer{

    public ClassicCustomersRequest(String name, String email) {
        super(name, email);
    }
}
