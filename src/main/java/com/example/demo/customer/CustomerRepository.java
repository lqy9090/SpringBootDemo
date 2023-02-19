package com.example.demo.customer;

import com.example.demo.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 14:53
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT CASE WHEN COUNT(c)>0 THEN TRUE ELSE FALSE END FROM Customer c where c.email = ?1")
    Boolean selectExistsEmail(String email);

    List<Customer> findCustomersByNameAndEmailOrderByIdDesc(String name, String email);

    @Query(value = "FROM Customer c WHERE c.name = ?1 and c.email = ?2 order by c.id desc ")
    List<Customer> findCustomersByConditionOrderByIdDesc(String name, String email);

    @Query(value = "SELECT * FROM customer c WHERE c.name = ?1 and c.email = ?2 order by c.id desc", nativeQuery = true)
    List<Customer> findCustomersByConditionOrderByIdDesc2(String name, String email);

    @Query(value = "UPDATE Customer c SET c.password = ?1 WHERE c.id = ?2", nativeQuery = true)
    void updateCustomerById(String password, Long id);


}
