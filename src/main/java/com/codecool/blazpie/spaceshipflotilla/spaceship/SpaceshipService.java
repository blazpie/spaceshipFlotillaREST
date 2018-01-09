package com.codecool.blazpie.spaceshipflotilla.spaceship;

import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMember;
import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpaceshipService {

    SpaceshipRepository spaceshipRepository;
    CrewMemberRepository crewMemberRepository;

    public SpaceshipService(SpaceshipRepository spaceshipRepository, CrewMemberRepository crewMemberRepository) {
        this.spaceshipRepository = spaceshipRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    public void create(Spaceship spaceship) {
        for (CrewMember cm : spaceship.getCrewMembers()) {
            Spaceship oldShip = spaceshipRepository.findByCrewMembers(cm);
            if (oldShip != null) {
                removeCrewMember(oldShip, cm.getId());
            }
        }
        spaceshipRepository.save(spaceship);
    }

    public Iterable<Spaceship> getAll() {
        return spaceshipRepository.findAll();
    }

    public Spaceship getById(Integer id) {
        return spaceshipRepository.findOne(id);
    }

    public void delete(Integer id) {
        spaceshipRepository.delete(id);
    }

    public Iterable<CrewMember> showCrew(Integer id) {
        return spaceshipRepository.findOne(id).getCrewMembers();
    }

    public void addCrewMember(Spaceship spaceship, Integer crewMemberId) {
        CrewMember cm = crewMemberRepository.findOne(crewMemberId);
        Spaceship oldShip = spaceshipRepository.findByCrewMembers(cm);
        if (oldShip != null) {
            removeCrewMember(oldShip, crewMemberId);
        }
        spaceship.getCrewMembers().add(cm);
        spaceshipRepository.save(spaceship);
    }

    public void removeCrewMember(Spaceship spaceship, Integer crewMemberId) {
        spaceship.getCrewMembers().remove(crewMemberRepository.findOne(crewMemberId));
        spaceshipRepository.save(spaceship);
    }

    public Spaceship mapDTO(SpaceshipDTO spaceshipDTO) {
        Spaceship spaceship = new Spaceship();
        spaceship.setId(spaceshipDTO.getId());
        spaceship.setName(spaceshipDTO.getName());

        List<CrewMember> crewMembers = new ArrayList<>();
        for (Integer id : spaceshipDTO.getCrewMembers()) {
            crewMembers.add(crewMemberRepository.findOne(id));
        }
        spaceship.setCrewMembers(crewMembers);

        return spaceship;

    }
}
