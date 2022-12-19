package com.example.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:53
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
