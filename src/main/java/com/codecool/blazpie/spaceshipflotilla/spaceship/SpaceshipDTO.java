package com.codecool.blazpie.spaceshipflotilla.spaceship;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SpaceshipDTO {

    Integer id;
    String name;
    List<Integer> crewMembers;

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

    public List<Integer> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<Integer> crewMembers) {
        this.crewMembers = crewMembers;
    }
}