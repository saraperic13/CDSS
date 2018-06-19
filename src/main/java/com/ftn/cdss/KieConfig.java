package com.ftn.cdss;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.enterprise.context.ApplicationScoped;

@Configuration
public class KieConfig {

    private static final String KIE_SESSION_NAME = "CdssSession";

    @Autowired
    private KieContainer kieContainer;

    @Bean
    @ApplicationScoped
    public KieContainer kieContainer() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId(
                "com.ftn.cdss", "cdss.rules", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);

        return kContainer;
    }


    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KieSession kieSession() {
        return kieContainer.newKieSession(KIE_SESSION_NAME);
    }
}
