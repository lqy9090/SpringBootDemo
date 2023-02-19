package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2023/1/3 16:11
 **/
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void selectExistsEmail() {
        //given
        String email = "jamilia@gmail.com";
        Customer customer = new Customer(
                1L,
                "Jamilia",
                "p@ssword",
                email
        );
        underTest.save(customer);

        //when
        Boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void selectNotExistsEmail() {
        //given
        String email = "jamilia@gmail.com";

        //when
        Boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }
}