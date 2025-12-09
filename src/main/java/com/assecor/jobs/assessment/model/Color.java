package com.assecor.jobs.assessment.model;

public enum Color {
    BLUE(1, "blau"),
    GREEN(2, "grün"),
    VIOLETT(3, "violett"),
    RED(4, "rot"),
    YELLOW(5, "gelb"),
    TURQUIS(6, "türkis"), //?
    WHITE(7, "weiß");

    private int number;
    private String colorName;

    private Color(final int number, final String colorName) {
        this.number = number;
        this.colorName = colorName;
    }

    public static Color getByNumber(final int number) {
        Color foundColor = null;

        for (Color color : Color.values()) {
            if (color.getNumber() == number) {
                foundColor = color;
                break;
            }
        }

        return foundColor;
    }

    public int getNumber() {
        return this.number;
    }

    public String getColorName() {
        return this.colorName;
    }

    @Override
    public String toString() {
        return this.colorName;
    }
}
