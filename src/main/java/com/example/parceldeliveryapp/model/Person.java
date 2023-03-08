package com.example.parceldeliveryapp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "typeId")
    private Integer typeId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "access_mode")
    private Integer accessMode;

    public Person() {
    }

    public Person( Integer typeId, String firstName, String lastName, String email,
                   String position, String password, Integer accessMode) {
       // this.id = id;
        this.typeId = typeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = position;
        this.password = password;
        this.accessMode = accessMode;
    }
}
