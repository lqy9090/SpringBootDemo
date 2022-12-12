package com.example.demo.customer;

import java.util.Collections;
import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:53
 **/
public class CustomerRepository implements CustomerRepo{
    @Override
    public List<Customer> getCustomer() {
        //TODO connected real DB
        return Collections.singletonList(
                new Customer(1L, "TODO connected real DB", "todo")
        );
    }
}
