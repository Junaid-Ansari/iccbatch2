package com.wellsfargo.pad.iccbatch.domain;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "People")
public class People {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    //@CreatedDate
    private LocalDateTime createdDate;
    //@LastModifiedDate
    private LocalDateTime lastModifieDate;

    public People(int id, String firstName, String lastName, String email, String mobileNumber) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifieDate() {
        return lastModifieDate;
    }

    public void setLastModifieDate(LocalDateTime lastModifieDate) {
        this.lastModifieDate = lastModifieDate;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName + " email " + email;
    }

}