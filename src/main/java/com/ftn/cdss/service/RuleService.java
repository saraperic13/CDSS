package com.ftn.cdss.service;

import org.apache.maven.shared.invoker.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.UUID;

@Service
public class RuleService {

    private static final String RULE_PATH = "cdss-rules/src/main/resources/diagnose/";

    public void saveRule(String rule) {

        final String rulePath = RULE_PATH + UUID.randomUUID().toString() + ".drl";
        try {
            final PrintWriter out = new PrintWriter(new FileOutputStream(rulePath));
            out.println(rule);
            out.close();

            installSiemRules();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void installSiemRules() {

        final InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("cdss-rules/pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        final Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("apache-maven-3.5.3"));

        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
