package com.example.demo.customer;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.customer.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Arrays;


/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2023/1/4 14:38
 **/
@WebMvcTest
class CustomerController2Test {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomer() throws Exception {
        //given
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

        //when-then
        when(customerService.getCustomers()).thenReturn(Arrays.asList(qiuyi, quinn));
        ResultActions resultActions = this.mockMvc.perform(get("/api/v2/customer"))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    void createNewCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}