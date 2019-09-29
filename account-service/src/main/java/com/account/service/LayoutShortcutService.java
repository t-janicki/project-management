package com.account.service;

import com.account.domain.layout.Shortcut;

import java.util.List;

public interface LayoutShortcutService {

    Shortcut createDefaultLayoutShortcut();

    Shortcut assignDefaultShortcut(Long id);

    List<String> addLayoutShortcut(Long userId, List<Shortcut> shortcuts);

    String[] getLayoutShortcuts(Long userId);

    String[] getLayoutShortcutsFromAccountId(Long id);

}
