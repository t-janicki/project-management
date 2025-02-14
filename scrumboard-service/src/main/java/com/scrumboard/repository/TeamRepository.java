package com.scrumboard.repository;

import com.scrumboard.domain.Member;
import com.scrumboard.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByMembersInAndIsDeletedIsFalse(List<Member> members);

    Optional<Team> findByIdAndMembersInAndIsDeletedIsFalse(Long teamId, List<Member> members);
}
