package com.example.application.data.entity.ViCon;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PreferenceItems")
public class PreferenceItem {
    @Id
    private String ItemID;
    @Column
    private int Version = 1;
    @Column
    private String Name;
    @Column
    private String Description;

}
