package ru.curs.Clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Table(name="doctor")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_seq")
    @SequenceGenerator(name = "doc_seq", sequenceName = "doc_sequence", allocationSize = 1)
    public Long id;

    @Basic
    private String docName;

    public Long getId() {
        return id;
    }

    public Doctor(){}
    public Doctor(String docName){
        this.docName = docName;
    }
}
