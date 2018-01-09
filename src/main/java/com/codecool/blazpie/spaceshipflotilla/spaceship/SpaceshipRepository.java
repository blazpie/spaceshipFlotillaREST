package com.codecool.blazpie.spaceshipflotilla.spaceship;

import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipRepository extends CrudRepository<Spaceship,Integer> {
    public Spaceship findByCrewMembers(CrewMember crewMember);
}
