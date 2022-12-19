package com.example.demo.jsonplaceholder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/19 16:48
 **/
//@Configuration
public class JSONPlaceHolderConfiguration {

    @Bean
    CommandLineRunner runner(JSONPlaceHolderClient jsonPlaceHolderClient) {
        return args -> {
            System.out.println("https://jsonplaceholder.typicode.com/posts");
            System.out.println(jsonPlaceHolderClient.getPosts().size());

            System.out.println("https://jsonplaceholder.typicode.com/posts");
            System.out.println(jsonPlaceHolderClient.getPost(1));
        };
    }
}
