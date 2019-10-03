package com.scrumboard.repository;

import com.scrumboard.domain.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckItemRepository extends JpaRepository<CheckItem, Long> {
}
