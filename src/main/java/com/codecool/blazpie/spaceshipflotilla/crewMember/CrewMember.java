package com.codecool.blazpie.spaceshipflotilla.crewMember;

import com.codecool.blazpie.spaceshipflotilla.spaceship.Spaceship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String firstName;
    String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
