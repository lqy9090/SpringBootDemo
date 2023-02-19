package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;

import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 16:34
 **/
public interface CustomerRepo {
    List<Customer> getCustomer();
}
