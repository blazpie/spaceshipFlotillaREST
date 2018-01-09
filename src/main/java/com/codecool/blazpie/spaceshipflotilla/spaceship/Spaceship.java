package com.codecool.blazpie.spaceshipflotilla.spaceship;

import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMember;

import javax.persistence.*;
import java.util.List;

@Entity
public class Spaceship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    List<CrewMember> crewMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
