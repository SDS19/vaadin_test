package com.example.application.data.ViCon.repository;

import com.example.application.data.ViCon.entity.PreferenceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceItemRepository extends JpaRepository<PreferenceItem, String> {

    List<PreferenceItem> findByPatientId(String patientId);
}
