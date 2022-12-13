package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:48
 **/

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    List<Customer> getCustomers() {
        return customerRepo.getCustomer();
    }

    Customer getCustomer(Long id) {
        return customerRepo.getCustomer()
                .stream()
                .filter(customer -> id.equals(customer.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("customer not found"));
    }
}
