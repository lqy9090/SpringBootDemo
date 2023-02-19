package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import com.example.demo.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/20 16:46
 **/
@ExtendWith(MockitoExtension.class)
class CustomerServiceTestWithMock {

    private CustomerService underTest;

    @Mock
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
        //when
        underTest.getCustomers();

        //then
//       verify(customerRepository).findAll(); //pass
        verify(customerRepository).deleteAll(); //fail
    }

    @Test
    void canAddCustomerTest() {
        //given
        Customer customer = new Customer(
                "Qiuyi",
                "qiuP@ssword",
                "qiuyi@gmail.com"
        );
        //when
        underTest.addCustomer(customer);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).save(customerArgumentCaptor.capture());

        Customer captorValue = customerArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(customer);
    }

    @Test
    void willThrowEmailTaken() {
        //given
        Customer customer = new Customer(
                "Qiuyi",
                "qiuP@ssword",
                "qiuyi@gmail.com"
        );
        given(customerRepository.selectExistsEmail(customer.getEmail())).willReturn(true); //打桩

        //when
        //then
        assertThatThrownBy(() -> underTest.addCustomer(customer))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining( "Email " + customer.getEmail() + " taken");

        verify(customerRepository, never()).save(any());

    }

    @Test
    void deleteCustomerTest() {
        //given
        when(customerRepository.existsById(1L)).thenReturn(true);

        //when
        underTest.deleteCustomer(1L);
        //then
        verify(customerRepository).deleteById(1L);

    }
}