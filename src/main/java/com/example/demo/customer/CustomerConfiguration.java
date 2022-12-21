package com.example.demo.customer;

import com.example.demo.appInfo.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/11 18:07
 **/

@Configuration
public class CustomerConfiguration {

    @Value("${app.useFakeCustomerRepo:false}")
    private boolean useFakeCustomerRepo;

    @Value("${info.company.name}")
    private String companyName;

    @Autowired
    private Environment environment;

    public CustomerConfiguration() {
    }

    @Bean
    CommandLineRunner commandLineRunner(AppInfo appInfo) { //Inject

        return args -> {
            System.out.println("Command line runner working.");
            System.out.println(companyName);
            System.out.println(
                    environment.getProperty("info.company.name"));

            System.out.println(appInfo);
        };
    }

    @Bean
    CustomerRepo customerRepo() {
        System.out.println("useFakeCustomerRepo = " + useFakeCustomerRepo);
        return new CustomerFakeRepository();
    }


}
