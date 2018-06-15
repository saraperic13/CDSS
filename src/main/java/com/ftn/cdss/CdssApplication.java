package com.ftn.cdss;

import com.ftn.cdss.service.AccountService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CdssApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CdssApplication.class, args);
        context.getBean(AccountService.class).initAccounts();
    }

    @Bean
    public KieContainer kieContainer() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId(
                "com.ftn.cdss", "cdss.rules", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
        return kContainer;
    }
}
