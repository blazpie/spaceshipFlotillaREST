package com.codecool.blazpie.spaceshipflotilla.spaceship;

import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMember;
import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMemberRepository;
import com.codecool.blazpie.spaceshipflotilla.response.Response;
import com.codecool.blazpie.spaceshipflotilla.response.SuccessResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class SpaceshipService {

    SpaceshipRepository spaceshipRepository;
    CrewMemberRepository crewMemberRepository;

    public SpaceshipService(SpaceshipRepository spaceshipRepository, CrewMemberRepository crewMemberRepository) {
        this.spaceshipRepository = spaceshipRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    public Response create(Spaceship spaceship) {
        for (CrewMember cm : spaceship.getCrewMembers()) {
            Spaceship oldShip = spaceshipRepository.findSpaceshipByArchivedFalseAndCrewMembers(cm);
            if (oldShip != null) {
                removeCrewMember(oldShip, cm.getId());
            }
        }
        spaceship.setVisible();
        spaceshipRepository.save(spaceship);
        return new SuccessResponse("created");
    }

    public Iterable<Spaceship> getAll() {
        return spaceshipRepository.findAllByArchivedFalse();
    }

    public Spaceship getById(Integer id) {
        return spaceshipRepository.findSpaceshipByArchivedFalseAndId(id);
    }

    public Response delete(Integer id) throws NoSuchElementException {
        Spaceship spaceship = spaceshipRepository.findSpaceshipByArchivedFalseAndId(id);
        if (spaceship == null) {
            throw new NoSuchElementException("Requested resource does not exist: id " + id);
        }
        spaceship.archive();
        spaceshipRepository.save(spaceship);
        return new SuccessResponse("deleted");
    }

    public Iterable<CrewMember> showCrew(Integer id) {
        Spaceship spaceship = spaceshipRepository.findSpaceshipByArchivedFalseAndId(id);
        if (spaceship == null) {
            throw new NoSuchElementException("Requested resource does not exist: id " + id);
        }
        return spaceship.getCrewMembers();
    }

    public void addCrewMember(Spaceship spaceship, Integer crewMemberId) throws NoSuchElementException {
        CrewMember cm = crewMemberRepository.findCrewMemberByArchivedFalseAndId(crewMemberId);
        if (cm == null) {
            throw new NoSuchElementException("No resource found to add: 'id " + crewMemberId + "'");
        }
        Spaceship oldShip = spaceshipRepository.findSpaceshipByArchivedFalseAndCrewMembers(cm);
        if (oldShip != null) {
            removeCrewMember(oldShip, crewMemberId);
        }
        spaceship.getCrewMembers().add(cm);
        spaceshipRepository.save(spaceship);
    }

    public void removeCrewMember(Spaceship spaceship, Integer crewMemberId) {
        CrewMember cm = crewMemberRepository.findCrewMemberByArchivedFalseAndId(crewMemberId);
        if (cm == null) {
            throw new NoSuchElementException("No resource found to remove: 'id " + crewMemberId + "'");
        }
        spaceship.getCrewMembers().remove(cm);
        spaceshipRepository.save(spaceship);
    }

    public Spaceship mapDTO(SpaceshipDTO spaceshipDTO) {
        Spaceship spaceship = new Spaceship();
        spaceship.setId(spaceshipDTO.getId());
        spaceship.setName(spaceshipDTO.getName());
        List<CrewMember> crewMembers = new ArrayList<>();
        if (spaceshipDTO.getCrewMembers() != null) {
            for (Integer id : spaceshipDTO.getCrewMembers()) {
                CrewMember cm = crewMemberRepository.findCrewMemberByArchivedFalseAndId(id);
                if (cm == null) {
                    throw new NoSuchElementException("No resource found to add: 'id " + id + "'");
                } else {
                    crewMembers.add(crewMemberRepository.findCrewMemberByArchivedFalseAndId(id));
                }
            }
        }
        spaceship.setCrewMembers(crewMembers);
        return spaceship;

    }

    public Response update(Spaceship spaceship) throws NoSuchElementException {
        if (spaceshipRepository.findSpaceshipByArchivedFalseAndId(spaceship.getId()) == null) {
            throw new NoSuchElementException("Resource to update does not exist: 'id " + spaceship.getId() + "'");
        } else {
            spaceshipRepository.save(spaceship);
        }
        return new SuccessResponse("updated");
    }
}
