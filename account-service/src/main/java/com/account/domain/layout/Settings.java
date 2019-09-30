package com.account.domain.layout;

import com.account.domain.layout.settings.Layout;
import com.account.domain.layout.settings.theme.Theme;

import javax.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "custom_scrollbars")
    private Boolean customScrollbars = Boolean.TRUE;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Layout layout;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Theme theme;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shortcut shortcut;

    public Settings() {
    }

    public Settings(Long id, Boolean customScrollbars,
                    Layout layout, Theme theme) {
        this.id = id;
        this.customScrollbars = customScrollbars;
        this.layout = layout;
        this.theme = theme;
    }

    public Settings(Boolean customScrollbars,
                    Layout layout, Theme theme, Shortcut shortcut) {
        this.customScrollbars = customScrollbars;
        this.layout = layout;
        this.theme = theme;
        this.shortcut = shortcut;
    }

    public Long getId() {
        return id;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Boolean getCustomScrollbars() {
        return customScrollbars;
    }

    public void setCustomScrollbars(Boolean customScrollbars) {
        this.customScrollbars = customScrollbars;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Shortcut getShortcut() {
        return shortcut;
    }

    public void setShortcut(Shortcut shortcut) {
        this.shortcut = shortcut;
    }
}
