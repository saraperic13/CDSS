package com.ftn.cdss.monitoring;

import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.rules.DialysisNeededEvent;
import com.ftn.cdss.model.rules.HeartBeatEvent;
import com.ftn.cdss.model.rules.ICUPatient;
import com.ftn.cdss.model.rules.UrineEvent;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DialysisNeededTest {

    private final Integer PATIENT_1_SSN = 1;
    private final Integer PATIENT_2_SSN = 2;
    private final Integer PATIENT_3_SSN = 3;

    private final HashMap<Integer, ICUPatient> icuPatients = new HashMap<>();


    @Test
    public void testCEPConfigThroughKModuleXML() {

        final KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();
        final KieSession pseudoClockTimeSession = kContainer.newKieSession("kSessionPseudoClock");

        dialysisNeededTest(pseudoClockTimeSession);
    }

    private void dialysisNeededTest(KieSession kieSession) {

        final HashMap<Integer, FactHandle> facts = fillTheMapInsertInSession(kieSession);

        final SessionPseudoClock clock = kieSession.getSessionClock();
        final MedicalChart medicalChart = createMedicalChart(PATIENT_1_SSN);
        kieSession.insert(medicalChart);

        // Insert urine events longer period
        for (int index = 0; index < 10; index++) {

            // Change blood oxygen level of the first patient
            kieSession.insert(simulateUrineChange(PATIENT_1_SSN, 200));

            clock.advanceTime(1, TimeUnit.HOURS);
            int ruleCount = kieSession.fireAllRules();

            assertThat(ruleCount, equalTo(0));
        }

        kieSession.insert(simulateUrineChange(PATIENT_1_SSN, 10));
        clock.advanceTime(15, TimeUnit.HOURS);
        int ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(0));

        // Insert Heartbeats events
        for (int index = 0; index < 15; index++) {

            kieSession.insert(simulateHeartbeat(PATIENT_1_SSN));
            clock.advanceTime(1, TimeUnit.MICROSECONDS);

            ruleCount = kieSession.fireAllRules();
            assertThat(ruleCount, equalTo(0));
        }

        ruleCount = kieSession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        Collection<?> dialysisNeededEvents = kieSession.getObjects(new ClassObjectFilter(DialysisNeededEvent.class));
        assertThat(dialysisNeededEvents.size(), equalTo(1));
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

    private MedicalChart createMedicalChart(Integer ssn) {
        final MedicalChart medicalChart_1 = new MedicalChart();
        medicalChart_1.setSsn(ssn);

        final Disease kidneyDisease = new Disease();
        kidneyDisease.setName("Hronična bubrežna bolest");

        final Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDisease(kidneyDisease);

        final Set<Diagnosis> diagnosisSet = new HashSet<>();

        diagnosisSet.add(diagnosis);
        medicalChart_1.setDiagnosis(diagnosisSet);
        return medicalChart_1;
    }

    private ICUPatient createPatient(Integer ssn) {
        final ICUPatient patient = new ICUPatient();
        patient.setSsn(ssn);
        patient.setBloodOxygenLevel(70);
        return patient;
    }

    private UrineEvent simulateUrineChange(Integer ssn, double change) {
        return new UrineEvent(ssn, change);
    }

    private HeartBeatEvent simulateHeartbeat(Integer ssn) {
        return new HeartBeatEvent(ssn);
    }


}
