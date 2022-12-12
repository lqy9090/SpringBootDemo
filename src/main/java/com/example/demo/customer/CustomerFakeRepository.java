package com.example.demo.customer;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 16:34
 **/

public class CustomerFakeRepository implements CustomerRepo{
    @Override
    public List<Customer> getCustomer() {
        return Arrays.asList(
                new Customer(1L, "Quinn Lu", "password123"),
                new Customer(2L, "QiuYi Lu", "123password")
        );
    }
}
