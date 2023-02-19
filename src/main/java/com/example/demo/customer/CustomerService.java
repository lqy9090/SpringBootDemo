package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.entity.ClassicCustomersRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:48
 **/

@Service
public class CustomerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    List<Customer> getCustomers() {
        LOGGER.info("The getCustomers was called");
        return customerRepository.findAll();
    }

    Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException(
                                    "customer with id " + id + " not found");

                            LOGGER.error("Get customer {}", id, notFoundException);
                            return notFoundException;
                        });
    }

    public void addCustomer(Customer Customer) {
        Boolean existsEmail = customerRepository.selectExistsEmail(Customer.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + Customer.getEmail() + " taken");
        }

        customerRepository.save(Customer);
    }

    public void updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        if (optionalCustomer.isEmpty()) {
            throw new BadRequestException(
                    "Customer  " + customer.getId() + " not exist!");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long CustomerId) {
        if(!customerRepository.existsById(CustomerId)) {
            throw new NotFoundException(
                    "Customer with id " + CustomerId + " does not exists");
        }
        customerRepository.deleteById(CustomerId);
    }

    public List<Customer> getCustomersByConditionWithMethodName(ClassicCustomersRequest request) {
        System.out.println("getCustomers condition: " + request);
        List<Customer> customers = customerRepository.findCustomersByNameAndEmailOrderByIdDesc(request.getName(), request.getEmail());
        return customers;
    }

    public List<Customer> getCustomersByConditionWithJPQL(ClassicCustomersRequest request) {
        System.out.println("getCustomers condition: " + request);
        List<Customer> customers = customerRepository.findCustomersByConditionOrderByIdDesc(request.getName(), request.getEmail());
        return customers;
    }

    public List<Customer> getCustomersByConditionWithNativeSQL(ClassicCustomersRequest request) {
        System.out.println("getCustomers condition: " + request);
        List<Customer> customers = customerRepository.findCustomersByConditionOrderByIdDesc2(request.getName(), request.getEmail());
        return customers;
    }


    @Transactional
    public void RegisterCustomerAnResetPassword(Customer customer) {//the method unreasonable,just used to learn transaction usage

        System.out.println("before save: " + customer);
        //register
       customerRepository.saveAndFlush(customer);
        System.out.println("after save: " + customer);

        //update
        customerRepository.updateCustomerById("P@ssword", customer.getId()+1);
    }
}
