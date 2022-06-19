package com.example.application;

import com.example.application.data.ViCon.entity.Patient;
import com.example.application.data.ViCon.entity.PreferenceItem;
import com.example.application.data.ViCon.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService service;

    @Test
    void savePatientTest(){
        service.savePatient("patient_1");
    }

    @Test
    List<PreferenceItem> getPreferenceByPatientIdTest(){
        Patient patient = service.getPatient("patient_1");
        List<PreferenceItem> items = service.getPreferenceByPatientId(patient.getId());
        System.out.println(items);
        return items;
    }

    @Test
    void updatePatientPreferenceTest(){
        List<PreferenceItem> items = getPreferenceByPatientIdTest();
        PreferenceItem item = items.get(0);
        //item.setStatus("abc");
        item.setStatus("unselected");
        items.set(0,item);
        service.updatePatientPreference(items);
    }
}
