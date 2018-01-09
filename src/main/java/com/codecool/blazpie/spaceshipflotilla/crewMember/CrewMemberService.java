package com.codecool.blazpie.spaceshipflotilla.crewMember;

import com.codecool.blazpie.spaceshipflotilla.spaceship.SpaceshipRepository;
import org.springframework.stereotype.Component;

@Component
public class CrewMemberService {

    CrewMemberRepository crewMemberRepository;
    SpaceshipRepository spaceshipRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository, SpaceshipRepository spaceshipRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.spaceshipRepository = spaceshipRepository;
    }

    public Iterable<CrewMember> getAll() {
        return crewMemberRepository.findAll();
    }

    public void create(CrewMember crewMember) {
        crewMemberRepository.save(crewMember);
    }

    public CrewMember getById(Integer id) {
        return crewMemberRepository.findOne(id);
    }

    public void delete(Integer id) {
        CrewMember cm = getById(id);
        spaceshipRepository.findByCrewMembers(cm).getCrewMembers().remove(cm);
        crewMemberRepository.delete(id);
    }
}
