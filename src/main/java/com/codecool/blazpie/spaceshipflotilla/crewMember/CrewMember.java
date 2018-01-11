package com.codecool.blazpie.spaceshipflotilla.crewMember;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @JsonIgnore
    Boolean archived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName (String firstName) throws IllegalArgumentException {
        if (firstName == null) {
            throw new IllegalArgumentException("Illegal value for 'firstName': " + firstName);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null) {
            throw new IllegalArgumentException("Illegal value for 'lastName': " + lastName);
        }
        this.lastName = lastName;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void archive() {
        archived = true;
    }

    public void setVisible() {
        archived = false;
    }
}
