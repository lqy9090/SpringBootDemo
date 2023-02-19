package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.entity.ClassicCustomersRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

//    @AfterEach
//    void tearDown() {
//        customerRepository.deleteAll();
//    }

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

    @Test
    void shouldUpdateCustomerSuccess() {
        Customer ali = new Customer(
                1L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        customerRepository.save(ali);
        Customer customer = underTest.getCustomer(ali.getId());
        System.out.println("create: " + customer);

        Customer aliUpdated = new Customer(ali.getId(), "aliUpdated", ali.getPassword(), ali.getEmail());

        underTest.updateCustomer(aliUpdated);

        Optional<Customer> customerUpdated = customerRepository.findById(ali.getId());
        System.out.println("update: "+customerUpdated);
    }

    @Test
    void shouldReturnCustomersWithConditionImplementByMethodName() {
        Customer ali = new Customer(
                1L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        Customer ali2 = new Customer(
                2L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        customerRepository.saveAll(Arrays.asList(ali, ali2));

        ClassicCustomersRequest request = new ClassicCustomersRequest(
                "ali",
                "ali@gmail.com"
        );

        List<Customer> customersByCondition = underTest.getCustomersByConditionWithMethodName(request);
        System.out.println(customersByCondition);
    }

    @Test
    void shouldReturnCustomersWithConditionImplementByJPQL() {
        Customer ali = new Customer(
                1L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        Customer ali2 = new Customer(
                2L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        customerRepository.saveAll(Arrays.asList(ali, ali2));

        ClassicCustomersRequest request = new ClassicCustomersRequest(
                "ali",
                "ali@gmail.com"
        );

        List<Customer> customersByCondition = underTest.getCustomersByConditionWithJPQL(request);
        System.out.println(customersByCondition);
    }

    @Test
    void shouldReturnCustomersWithConditionImplementByNativeSQL() {
        Customer ali = new Customer(
                1L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        Customer ali2 = new Customer(
                2L,
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );
        customerRepository.saveAll(Arrays.asList(ali, ali2));

        ClassicCustomersRequest request = new ClassicCustomersRequest(
                "ali",
                "ali@gmail.com"
        );

        List<Customer> customersByCondition = underTest.getCustomersByConditionWithNativeSQL(request);
        System.out.println(customersByCondition);
    }

    @Test
    void shouldAddOneCustomerWithIdSequence() {
        Customer ali = new Customer(
                "ali",
                "ali@ssword",
                "ali@gmail.com"
        );

        underTest.addCustomer(ali);

        List<Customer> customers = customerRepository.findCustomersByNameAndEmailOrderByIdDesc(ali.getName(), ali.getPassword());

        System.out.println(customers);
    }
}