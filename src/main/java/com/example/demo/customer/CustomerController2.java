package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import com.example.demo.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:45
 **/
@RestController
@RequestMapping("/api/v2/customer") //if hasn't this annotation,localhost:8080 404
public class CustomerController2 {
    private final CustomerService customerService;

    @Autowired
    public CustomerController2(CustomerService customerService) { //依赖注入,但此时对象还没实例化
        this.customerService = customerService;
//        this.customerService = new CustomerService(); //bad code;在构造函数实例化 会需要多次实例化?
    }

    @GetMapping
    List<Customer> getCustomer() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    Customer getCustomer(@PathVariable("customerId") Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping(path = "{customerId}/exception")
    Customer getCustomerException(@PathVariable("customerId") Long id) {
        throw new ApiRequestException("ApiRequestException for customer " + id);
    }

    @PostMapping
    void CreateNewCustomer(@Valid @RequestBody Customer customer) {
        System.out.println("Request create new customer...");
        System.out.println(customer);
    }

    @PutMapping
    void UpdateCustomer(@RequestBody Customer customer) {
        System.out.println("Request update customer...");
        System.out.println(customer);

    }

    @DeleteMapping(path = "{customerId}")
    void DeleteCustomer(@PathVariable("customerId") Long id) {
        System.out.println("Request delete customer with Id "+id);
    }

    @PostMapping("/RegisterAndResetPassword")
    void RegisterCustomerAnResetPassword(@Valid @RequestBody Customer customer) {
        System.out.println("Request update Customer ...");
        customerService.RegisterCustomerAnResetPassword(customer);
    }
}
