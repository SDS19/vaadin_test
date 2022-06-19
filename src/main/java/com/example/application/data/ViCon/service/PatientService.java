package com.example.application.data.ViCon.service;

import com.example.application.data.ViCon.entity.Patient;
import com.example.application.data.ViCon.entity.PreferenceItem;
import com.example.application.data.ViCon.repository.PatientRepository;
import com.example.application.data.ViCon.repository.PreferenceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PreferenceItemRepository preferenceItemRepository;

    public void savePatient(String name){
        Patient patient = new Patient(name);
        patientRepository.save(patient);
        preferenceItemRepository.saveAll(initPatientPreference(patient));
    }

    public Patient getPatient(String name){
        return patientRepository.findByName(name);
    }

    //all the initial status of preferenceItem are "unselected"
    private List<PreferenceItem> initPatientPreference(Patient patient){
        String[] preferenceItemNames = {"Finanzierung","Blutprobe","Biopsie der Haut","Persönliche Daten","Krebsforschung","Virusforschung"};

        List<PreferenceItem> list = new ArrayList<>();
        for (String name : preferenceItemNames) {
            PreferenceItem preferenceItem = new PreferenceItem(name);
            preferenceItem.setPatient(patient);
            list.add(preferenceItem);
        }

        return list;
    }

    //get patient object from session, get preferenceItems from TextField
    public List<PreferenceItem> getPreferenceByPatientId(String patientId) {
        return preferenceItemRepository.findByPatientId(patientId);
    }

    public int updatePatientPreference(List<PreferenceItem> preferenceItems){
        return preferenceItemRepository.saveAll(preferenceItems).size();
    }
}
