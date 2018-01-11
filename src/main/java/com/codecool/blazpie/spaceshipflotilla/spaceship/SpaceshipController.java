package com.codecool.blazpie.spaceshipflotilla.spaceship;

import com.codecool.blazpie.spaceshipflotilla.crewMember.CrewMember;
import com.codecool.blazpie.spaceshipflotilla.response.Response;
import com.codecool.blazpie.spaceshipflotilla.response.SuccessResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/spaceships")
public class SpaceshipController {

    SpaceshipService service;

    public SpaceshipController(SpaceshipService service) {
        this.service = service;
    }

    @GetMapping(path = "")
    public Iterable<Spaceship> index() {
        return service.getAll();
    }

    @PostMapping(path = "")
    public Response create(@RequestBody SpaceshipDTO spaceshipdto) {
        return service.create(service.mapDTO(spaceshipdto));
    }

    @GetMapping(path = "/{id}")
    public Spaceship show(@PathVariable Integer id) { return service.getById(id); }

    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping(path = "/{id}")
    public Response update(@RequestBody SpaceshipDTO spaceshipdto, @PathVariable Integer id) {
        spaceshipdto.setId(id);
        return service.update(service.mapDTO(spaceshipdto));
    }
    @GetMapping(path = "/{id}/crew")
    public Iterable<CrewMember> showCrew(@PathVariable Integer id) {
        return service.showCrew(id);
    }

    @PostMapping(path = "/{id}/crew")
    public Response addCrew(@RequestBody List<Integer> crewMemberIdList, @PathVariable Integer id) {
        Spaceship spaceship = service.getById(id);
        for (Integer crewMemberId : crewMemberIdList) {
            service.addCrewMember(spaceship, crewMemberId);
        }
        return new SuccessResponse("updated");
    }

    @DeleteMapping(path = "/{id}/crew")
    public Response removeCrewMember(@RequestBody List<Integer> crewMemberIdList, @PathVariable Integer id) {
        Spaceship spaceship = service.getById(id);
        for (Integer crewMemberId : crewMemberIdList) {
            service.removeCrewMember(spaceship, crewMemberId);
        }
        return new SuccessResponse("updated");
    }
}
