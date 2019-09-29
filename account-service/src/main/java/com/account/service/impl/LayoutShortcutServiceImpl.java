package com.account.service.impl;

import com.account.domain.User;
import com.account.domain.layout.Shortcut;
import com.account.repository.layout.LayoutShortcutRepository;
import com.account.service.LayoutShortcutService;
import com.account.service.UserService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("Duplicates")
public class LayoutShortcutServiceImpl implements LayoutShortcutService {
    private UserService userService;
    private LayoutShortcutRepository shortcutRepository;

    @Autowired
    public LayoutShortcutServiceImpl(UserService userService,
                                     LayoutShortcutRepository shortcutRepository) {
        this.userService = userService;
        this.shortcutRepository = shortcutRepository;
    }

    public Shortcut createDefaultLayoutShortcut() {
        return new Shortcut("0");
    }

    public Shortcut assignDefaultShortcut(Long id) {
        User user = userService.getUserById(id);

        Shortcut shortcut = createDefaultLayoutShortcut();

        shortcut.setUser(user);

        shortcutRepository.save(shortcut);

        return shortcut;
    }

    public List<String> addLayoutShortcut(Long userId, List<Shortcut> shortcuts) {
        User user = userService.getUserById(userId);

        Shortcut result = shortcutRepository.findByUserId(user.getId())
                .orElse(new Shortcut());

        if (!shortcuts.isEmpty()) {
            Set<String> shortcutsNames = shortcuts.stream()
                    .map(Shortcut::getShortcut)
                    .collect(Collectors.toSet());

            String shortcutsAsString = String.join(", ", shortcutsNames);

            result.setShortcut(shortcutsAsString);
            result.setUser(user);
        }

        if (shortcuts.isEmpty()) {
            result.setShortcut("0");
            result.setUser(user);
        }

        shortcutRepository.save(result);

        return new ArrayList<>();
    }

    public String[] getLayoutShortcuts(Long userId) {
        Shortcut result = shortcutRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Shortcut not found"));

        String shortcutName = result.getShortcut();

        return shortcutName.split(", ");
    }

    public String[] getLayoutShortcutsFromAccountId(Long id) {
        Shortcut result = shortcutRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException("Shortcut not found"));

        String shortcutName = result.getShortcut();

        return shortcutName.split(", ");
    }
}
