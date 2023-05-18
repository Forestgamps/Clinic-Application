package ru.curs.Clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Entity
@Table(name="talon")
@Getter
@Setter
public class Talon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talon_seq")
    @SequenceGenerator(name = "talon_seq", sequenceName = "talon_sequence", allocationSize = 1)
    public Long id;
    @Basic
    private String firstName;
    @Basic
    private String secondName;
    @Basic
    private String docName;
    @Basic
    private String docSpec;
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Talon(){}

    public Long getId() {
        return id;
    }

    public Talon(String firstName, String secondName, Date date){
        this.date = date;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
