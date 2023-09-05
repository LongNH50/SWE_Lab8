package edu.mum.cs.cs425.studentmgmt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transcriptId;
    private String degreeTitle;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_transcript")
    private Student student;

    public Transript(String degreeTitle, Student student) {
        this.degreeTitle = degreeTitle;
        this.student = student;
    }
}
