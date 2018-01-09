package com.codecool.blazpie.spaceshipflotilla.crewMember;

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
    public void create(@RequestBody CrewMember crewMember) {
        service.create(crewMember);
    }

    @GetMapping(path = "/{id}")
    public CrewMember show(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@RequestBody CrewMember crewMember, @PathVariable Integer id) {
        crewMember.setId(id);
        service.create(crewMember);
    }
}