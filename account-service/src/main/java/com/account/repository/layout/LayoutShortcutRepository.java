package com.account.repository.layout;


import com.account.domain.layout.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutShortcutRepository extends JpaRepository<Shortcut, Long> {
}
