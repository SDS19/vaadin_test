package com.example.application.data.ViCon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    private String id;
    @Column
    private String name;

    @OneToMany
    @JoinColumn(name = "patientId")
    private List<PreferenceItem> preferenceItems;

    public Patient(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
