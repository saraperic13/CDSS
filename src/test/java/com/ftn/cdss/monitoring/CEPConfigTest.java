package com.ftn.cdss.monitoring;

import com.ftn.cdss.model.rules.BloodOxygenLevelChangeEvent;
import com.ftn.cdss.model.rules.HeartBeatEvent;
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

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class CEPConfigTest {

    private final Long PATIENT_1_SSN = 1L;
    private final Long PATIENT_2_SSN = 2L;
    private final Long PATIENT_3_SSN = 3L;

    private final HashMap<Long, ICUPatient> icuPatients = new HashMap<>();

    @Test
    public void testCEPConfigThroughCode() {

        final KieServices kieServices = KieServices.Factory.get();
        final KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("monitoring/monitoring-rules.drl"));
        final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Coudln't build knowledge module" + kieBuilder.getResults());
        }

        final KieContainer kContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        final KieBaseConfiguration kbConf = kieServices.newKieBaseConfiguration();
        kbConf.setOption(EventProcessingOption.STREAM);
        final KieBase kieBase = kContainer.newKieBase(kbConf);

        final KieSessionConfiguration realTimeClockConfig = kieServices.newKieSessionConfiguration();
        realTimeClockConfig.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        final KieSession realTimeClockSession = kieBase.newKieSession(realTimeClockConfig, null);

        final KieSessionConfiguration pseudoClockConfig = kieServices.newKieSessionConfiguration();
        pseudoClockConfig.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        final KieSession pseudoClockTimeSession = kieBase.newKieSession(pseudoClockConfig, null);

//        runRealtimeClockExample(realTimeClockSession);
        lowOxygenLevelTest(pseudoClockTimeSession);
    }

    @Test
    public void testCEPConfigThroughKModuleXML() {

        final KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();
        final KieSession realTimeClockSession = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
        final KieSession pseudoClockTimeSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");

//        runRealtimeClockExample(realTimeClockSession);
        lowOxygenLevelTest(pseudoClockTimeSession);
    }

    private ICUPatient createPatient(Long ssn) {
        final ICUPatient patient = new ICUPatient();
        patient.setSsn(ssn);
        patient.setBloodOxygenLevel(70);
        return patient;
    }

    private HashMap<Long, FactHandle> fillTheMapInsertInSession(KieSession kieSession){
        icuPatients.clear();
        icuPatients.put(PATIENT_1_SSN, createPatient(PATIENT_1_SSN));
        icuPatients.put(PATIENT_2_SSN, createPatient(PATIENT_2_SSN));
        icuPatients.put(PATIENT_3_SSN, createPatient(PATIENT_3_SSN));

        final HashMap<Long, FactHandle> patientsFacts = new HashMap<>();
        for(ICUPatient patient: icuPatients.values()){
            patientsFacts.put(patient.getSsn(), kieSession.insert(patient));
        }
        return patientsFacts;
    }

    private BloodOxygenLevelChangeEvent simulateBloodChange(Long ssn, double change){
        icuPatients.get(ssn).setBloodOxygenLevel(icuPatients.get(ssn).getBloodOxygenLevel() + change);
        return  new BloodOxygenLevelChangeEvent(ssn, change);
    }

    private void lowOxygenLevelTest(KieSession kieSession) {

        final HashMap<Long, FactHandle> facts = fillTheMapInsertInSession(kieSession);
        final SessionPseudoClock clock = kieSession.getSessionClock();

        for (int index = 0; index < 30; index++) {

            // Change blood oxygen level of the first patient
            final BloodOxygenLevelChangeEvent bloodOxygenLevelChangeEvent
                    = simulateBloodChange(PATIENT_1_SSN, 1);
            kieSession.insert(bloodOxygenLevelChangeEvent);
            kieSession.update(facts.get(PATIENT_1_SSN), icuPatients.get(PATIENT_1_SSN));

            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();

            assertThat(ruleCount, equalTo(0));
        }
        //Advance time 15 minutes, without a change in an oxygen level
        clock.advanceTime(15, TimeUnit.MINUTES);
        int ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(0));

        // Reduce blood oxygen level under 70, of the first patient
        final BloodOxygenLevelChangeEvent bloodOxygenLevelChangeEvent
                = simulateBloodChange(PATIENT_1_SSN, -31);
        kieSession.insert(bloodOxygenLevelChangeEvent);
        kieSession.update(facts.get(PATIENT_1_SSN), icuPatients.get(PATIENT_1_SSN));

        clock.advanceTime(16, TimeUnit.MINUTES);
        ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        Collection<?> numOfLowOxygenLevelEvent = kieSession.getObjects(new ClassObjectFilter(LowOxygenLevelEvent.class));
        assertThat(numOfLowOxygenLevelEvent.size(), equalTo(1));
    }

    private void runRealtimeClockExample(KieSession ksession) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int index = 0; index < 4; index++) {
                    HeartBeatEvent beep = new HeartBeatEvent();
                    ksession.insert(beep);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //do nothing
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //do nothing
        }
        ksession.fireUntilHalt();
        Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(BloodOxygenLevelChangeEvent.class));
        assertThat(newEvents.size(), equalTo(1));
    }
}
