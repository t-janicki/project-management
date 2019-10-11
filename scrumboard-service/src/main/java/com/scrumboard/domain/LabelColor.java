package com.scrumboard.domain;

public enum LabelColor {
    BG_RED("bg-red"),
    BG_RED_DARK("bg-red-dark text-white"),
    BG_RED_DARKER("bg-red-darker text-white"),
    BG_ORANGE("bg-orange text-white"),
    BG_ORANGE_DARK("bg-orange-dark text-white"),
    BG_ORANGE_DARKER("bg-orange-darker text-white"),
    BG_YELLOW("bg-yellow text-black"),
    BG_YELLOW_DARK("bg-yellow-dark text-black"),
    BG_YELLOW_DARKER("bg-yellow-darker text-white"),
    BG_GREEN("bg-green text-white"),
    BG_GREEN_DARK("bg-green-dark text-white"),
    BG_GREEN_DARKER("bg-green-darker text-white"),
    BG_TEAL("bg-teal text-white"),
    BG_TEAL_DARK("bg-teal-dark text-white"),
    BG_TEAL_DARKER("bg-teal-darker text-white"),
    BG_BLUE("bg-blue text-white"),
    BG_BLUE_DARK("bg-blue-dark text-white"),
    BG_BLUE_DARKER("bg-blue-darker text-white"),
    BG_INDIGO("bg-indigo text-white"),
    BG_INDIGO_DARK("bg-indigo-dark text-white"),
    BG_INDIGO_DARKER("bg-indigo-darker text-white"),
    BG_PURPLE("bg-purple text-white"),
    BG_PURPLE_DARK("bg-purple-dark text-white"),
    BG_PURPLE_DARKER("bg-purple-darker text-white"),
    BG_PINK("bg-pink text-white"),
    BG_PINK_DARK("bg-pink-dark text-white"),
    BG_PINK_DARKER("bg-pink-darker text-white"),
    BG_GREY_DARKER("bg-grey-darker text-white");

    public final String color;

    LabelColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
