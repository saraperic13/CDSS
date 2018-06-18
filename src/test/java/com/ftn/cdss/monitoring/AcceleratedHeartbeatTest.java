package com.ftn.cdss.monitoring;

import com.ftn.cdss.model.rules.AcceleratedHeartbeatEvent;
import com.ftn.cdss.model.rules.HeartBeatEvent;
import com.ftn.cdss.model.rules.ICUPatient;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class AcceleratedHeartbeatTest {

    private final Long PATIENT_1_SSN = 1L;
    private final Long PATIENT_2_SSN = 2L;
    private final Long PATIENT_3_SSN = 3L;

    private final HashMap<Long, ICUPatient> icuPatients = new HashMap<>();

    @Test
    public void testCEPConfigThroughKModuleXML() {

        final KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();
        final KieSession pseudoClockTimeSession = kContainer.newKieSession("kSessionPseudoClock");

        acceleratedHeartbeatTest(pseudoClockTimeSession);
    }

    private void acceleratedHeartbeatTest(KieSession kieSession) {

        fillTheMapInsertInSession(kieSession);

        final SessionPseudoClock clock = kieSession.getSessionClock();

        for (int index = 0; index < 30; index++) {

            // Insert heartbeat of the first patient
            kieSession.insert(simulateHeartbeat(PATIENT_1_SSN));

            // Insert heartbeat of the second patient
            kieSession.insert(simulateHeartbeat(PATIENT_2_SSN));

            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertThat(ruleCount, equalTo(0));
        }

        // SECOND PATIENT 2x heartbeats
        for (int index = 0; index < 15; index++) {

            // Insert heartbeat of the first patient
            kieSession.insert(simulateHeartbeat(PATIENT_1_SSN));

            // Insert heartbeat of the second patient
            kieSession.insert(simulateHeartbeat(PATIENT_2_SSN));
            kieSession.insert(simulateHeartbeat(PATIENT_2_SSN));

        }
        clock.advanceTime(9, TimeUnit.SECONDS);
        int ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        final Collection<?> numOfAcceleratedHeartbeatEvent =
                kieSession.getObjects(new ClassObjectFilter(AcceleratedHeartbeatEvent.class));
        assertThat(numOfAcceleratedHeartbeatEvent.size(), equalTo(1));
    }

    private ICUPatient createPatient(Long ssn) {
        final ICUPatient patient = new ICUPatient();
        patient.setSsn(ssn);
        patient.setBloodOxygenLevel(100);
        return patient;
    }

    private HashMap<Long, FactHandle> fillTheMapInsertInSession(KieSession kieSession) {
        icuPatients.clear();
        icuPatients.put(PATIENT_1_SSN, createPatient(PATIENT_1_SSN));
        icuPatients.put(PATIENT_2_SSN, createPatient(PATIENT_2_SSN));

        final HashMap<Long, FactHandle> patientsFacts = new HashMap<>();
        for (ICUPatient patient : icuPatients.values()) {
            patientsFacts.put(patient.getSsn(), kieSession.insert(patient));
        }
        return patientsFacts;
    }

    private HeartBeatEvent simulateHeartbeat(Long ssn) {
        return new HeartBeatEvent(ssn);
    }


}
