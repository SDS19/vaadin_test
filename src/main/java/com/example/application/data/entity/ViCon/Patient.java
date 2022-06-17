package com.example.application.data.entity.ViCon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    private String UserId;
    @Column
    private String Name;
    @Column
    private Date Birthday;
    @Column
    private boolean quickCheckCompleted;
    @Column
    private int quickCheckScore;
}
