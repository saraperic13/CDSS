package com.ftn.cdss;

import com.ftn.cdss.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CdssApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CdssApplication.class, args);
        context.getBean(AccountService.class).initAccounts();
    }
}
