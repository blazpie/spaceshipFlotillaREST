package com.codecool.blazpie.spaceshipflotilla.crewMember;

import com.codecool.blazpie.spaceshipflotilla.response.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "crew")
public class CrewMemberController {

    CrewMemberService service;

    public CrewMemberController(CrewMemberService service) {
        this.service = service;
    }

    @GetMapping(path = "")
    public Iterable<CrewMember> index() {
        return service.getAll();
    }

    @PostMapping(path = "")
    public Response create(@RequestBody CrewMember crewMember) {
        return service.create(crewMember);
    }

    @GetMapping(path = "/{id}")
    public CrewMember show(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping(path = "/{id}")
    public Response update(@RequestBody CrewMember crewMember, @PathVariable Integer id) {
        return service.update(crewMember);
    }
}