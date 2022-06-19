package com.example.application.data.ViCon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PreferenceItems")
public class PreferenceItem {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public PreferenceItem(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.status = "unselected";
    }
}
