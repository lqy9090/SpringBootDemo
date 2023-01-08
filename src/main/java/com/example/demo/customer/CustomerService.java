package com.example.demo.customer;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteCustomer(Long CustomerId) {
        if(!customerRepository.existsById(CustomerId)) {
            throw new NotFoundException(
                    "Customer with id " + CustomerId + " does not exists");
        }
        customerRepository.deleteById(CustomerId);
    }
}
