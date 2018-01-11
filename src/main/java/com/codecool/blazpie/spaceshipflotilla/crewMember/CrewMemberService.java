package com.codecool.blazpie.spaceshipflotilla.crewMember;

import com.codecool.blazpie.spaceshipflotilla.response.Response;
import com.codecool.blazpie.spaceshipflotilla.response.SuccessResponse;
import com.codecool.blazpie.spaceshipflotilla.spaceship.Spaceship;
import com.codecool.blazpie.spaceshipflotilla.spaceship.SpaceshipRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CrewMemberService {

    CrewMemberRepository crewMemberRepository;
    SpaceshipRepository spaceshipRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository, SpaceshipRepository spaceshipRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.spaceshipRepository = spaceshipRepository;
    }

    public Iterable<CrewMember> getAll() {
        return crewMemberRepository.findAllByArchivedFalse();
    }

    public Response create(CrewMember crewMember) {
        crewMember.setVisible();
        crewMemberRepository.save(crewMember);
        return new SuccessResponse("created");
    }

    public CrewMember getById(Integer id) {
        return crewMemberRepository.findCrewMemberByArchivedFalseAndId(id);
    }

    public Response delete(Integer id) throws NoSuchElementException {
        CrewMember cm = getById(id);
        Response response;
        if (cm != null) {
            Spaceship shipOfcm = spaceshipRepository.findSpaceshipByArchivedFalseAndCrewMembers(cm);
            if (shipOfcm != null) {
                shipOfcm.getCrewMembers().remove(cm);
            }
            cm.archive();
            crewMemberRepository.save(cm);
            response = new SuccessResponse("deleted");
        } else {
            throw new NoSuchElementException("Requested resource does not exist: id " + id);
        }
        return response;
    }

    public Response update(CrewMember crewMember) throws NoSuchElementException {
        if (crewMemberRepository.findCrewMemberByArchivedFalseAndId(crewMember.getId()) == null) {
            throw new NoSuchElementException("Resource to update does not exist: 'id " + crewMember.getId() + "'");
        } else {
            crewMemberRepository.save(crewMember);
            return new SuccessResponse("updated");
        }
    }
}
