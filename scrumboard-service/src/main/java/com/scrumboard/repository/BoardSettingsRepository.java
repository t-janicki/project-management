package com.scrumboard.repository;

import com.scrumboard.domain.BoardSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardSettingsRepository extends JpaRepository<BoardSettings, Long> {
}
