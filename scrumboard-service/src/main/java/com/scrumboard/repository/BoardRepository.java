package com.scrumboard.repository;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardType;
import com.scrumboard.domain.Member;
import com.scrumboard.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByIsDeletedIsFalseAndUserId(Long userId);

    Optional<Board> findByIdAndIsDeletedIsFalseAndUserId(Long boardId, Long userId);

    List<Board> findAllByUserIdAndBoardTypeAndIsDeletedIsFalse(Long userId, BoardType boardType);

}
