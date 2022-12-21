package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/20 16:46
 **/
@DataJpaTest
class CustomerServiceTest {

    private CustomerService underTest;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {
        Customer qiuyi = new Customer(
                1L,
                "Qiuyi",
                "qiuP@ssword",
                "qiuyi@gmail.com"
        );

        Customer quinn = new Customer(
                2L,
                "Quinn",
                "Quinn@ssword",
                "Quinn@gmail.com"
        );

        customerRepository.saveAll(Arrays.asList(qiuyi, quinn));
        List<Customer> customerList = underTest.getCustomers();

        assertEquals(2, customerList.size());
    }

    @Test
    void getCustomer() {
        Customer ali = new Customer(
                1L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );

        customerRepository.save(ali);
        Customer customer = underTest.getCustomer(1L);

        assertEquals(1L, customer.getId());
        assertEquals("ali", customer.getName() );
        assertEquals("ali@ssword", customer.getPassword() );
        assertEquals("ali@gmail.com", customer.getEmail() );
    }
}