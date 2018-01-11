package com.codecool.blazpie.spaceshipflotilla.crewMember;

import org.springframework.data.repository.CrudRepository;

public interface CrewMemberRepository extends CrudRepository<CrewMember,Integer> {

    public Iterable<CrewMember> findAllByArchivedFalse();
    public CrewMember findCrewMemberByArchivedFalseAndId(Integer id);
}
