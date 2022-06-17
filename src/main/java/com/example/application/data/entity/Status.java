package com.example.application.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Status extends AbstractEntity {

    private String name;

    public Status(String name) {
        this.name = name;
    }
}
