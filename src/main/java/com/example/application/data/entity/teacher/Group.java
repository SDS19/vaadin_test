package com.example.application.data.entity.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private List<Student> students;

    @ManyToMany
    @JoinTable(name = "t_group_curriculum",
               joinColumns = {@JoinColumn(name = "groupId")},
               inverseJoinColumns = {@JoinColumn(name = "curriculumId")})
    private List<Curriculum> curricula;
}
