package com.ftn.cdss.monitoring;

import com.ftn.cdss.model.rules.BloodOxygenLevelChangeEvent;
import com.ftn.cdss.model.rules.ICUPatient;
import com.ftn.cdss.model.rules.LowOxygenLevelEvent;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class LowOxygenBloodLevelTest {

    private final Integer PATIENT_1_SSN = 1;
    private final Integer PATIENT_2_SSN = 2;
    private final Integer PATIENT_3_SSN = 3;

    private final HashMap<Integer, ICUPatient> icuPatients = new HashMap<>();

    @Test
    public void testCEPConfigThroughCode() {

        final KieServices kieServices = KieServices.Factory.get();
        final KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("monitoring/monitoring-rules.drl"));
        final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Couldn't build knowledge module" + kieBuilder.getResults());
        }

        final KieContainer kContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        final KieBaseConfiguration kbConf = kieServices.newKieBaseConfiguration();
        kbConf.setOption(EventProcessingOption.STREAM);
        final KieBase kieBase = kContainer.newKieBase(kbConf);

        final KieSessionConfiguration realTimeClockConfig = kieServices.newKieSessionConfiguration();
        realTimeClockConfig.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));

        final KieSessionConfiguration pseudoClockConfig = kieServices.newKieSessionConfiguration();
        pseudoClockConfig.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        final KieSession pseudoClockTimeSession = kieBase.newKieSession(pseudoClockConfig, null);

        lowOxygenLevelTest(pseudoClockTimeSession);
    }

    @Test
    public void testCEPConfigThroughKModuleXML() {

        final KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();
        final KieSession pseudoClockTimeSession = kContainer.newKieSession("kSessionPseudoClock");

        lowOxygenLevelTest(pseudoClockTimeSession);
    }

    private void lowOxygenLevelTest(KieSession kieSession) {

        final HashMap<Integer, FactHandle> facts = fillTheMapInsertInSession(kieSession);
        final SessionPseudoClock clock = kieSession.getSessionClock();

        for (int index = 0; index < 30; index++) {

            // Change blood oxygen level of the first patient
            kieSession.insert(simulateBloodChange(PATIENT_1_SSN, 1));
            kieSession.update(facts.get(PATIENT_1_SSN), icuPatients.get(PATIENT_1_SSN));

            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();

            assertThat(ruleCount, equalTo(0));
        }

        //Advance time 15 minutes, without a change in an oxygen level
        clock.advanceTime(15, TimeUnit.MINUTES);
        int ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(0));

        // Reduce blood oxygen level (still above 70), of the first patient
        kieSession.insert(simulateBloodChange(PATIENT_1_SSN, -20));
        kieSession.update(facts.get(PATIENT_1_SSN), icuPatients.get(PATIENT_1_SSN));
        clock.advanceTime(15, TimeUnit.MINUTES);
        ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(0));

        // Reduce blood oxygen level under 70, of the first patient
        kieSession.insert(simulateBloodChange(PATIENT_1_SSN, -20));
        kieSession.update(facts.get(PATIENT_1_SSN), icuPatients.get(PATIENT_1_SSN));
        clock.advanceTime(16, TimeUnit.MINUTES);
        ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        Collection<?> numOfLowOxygenLevelEvent = kieSession.getObjects(new ClassObjectFilter(LowOxygenLevelEvent.class));
        assertThat(numOfLowOxygenLevelEvent.size(), equalTo(1));
    }

    private HashMap<Integer, FactHandle> fillTheMapInsertInSession(KieSession kieSession) {
        icuPatients.clear();
        icuPatients.put(PATIENT_1_SSN, createPatient(PATIENT_1_SSN));
        icuPatients.put(PATIENT_2_SSN, createPatient(PATIENT_2_SSN));
        icuPatients.put(PATIENT_3_SSN, createPatient(PATIENT_3_SSN));

        final HashMap<Integer, FactHandle> patientsFacts = new HashMap<>();
        for (ICUPatient patient : icuPatients.values()) {
            patientsFacts.put(patient.getSsn(), kieSession.insert(patient));
        }
        return patientsFacts;
    }

    private ICUPatient createPatient(Integer ssn) {
        final ICUPatient patient = new ICUPatient();
        patient.setSsn(ssn);
        patient.setBloodOxygenLevel(70);
        return patient;
    }

    private BloodOxygenLevelChangeEvent simulateBloodChange(Integer ssn, double change) {
        icuPatients.get(ssn).setBloodOxygenLevel(icuPatients.get(ssn).getBloodOxygenLevel() + change);
        return new BloodOxygenLevelChangeEvent(ssn, change);
    }


}
