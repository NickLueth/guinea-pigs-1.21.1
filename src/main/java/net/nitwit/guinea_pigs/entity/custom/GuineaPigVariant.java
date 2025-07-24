package net.nitwit.guinea_pigs.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum GuineaPigVariant {
    WHITE(0),
    TRICOLOR(1),
    RED(2),
    HIMALAYAN(3),
    GRAY(4),
    DUTCH(5),
    BROWN_DUTCH(6),
    BLACK_TAN(7),
    BLACK(8),
    ACORN_SQUASH(9);

    private static final GuineaPigVariant[] BY_ID = Arrays.stream(values()).
            sorted(Comparator.comparingInt(GuineaPigVariant::getId)).toArray(GuineaPigVariant[]::new);
    private final int id;

    GuineaPigVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static GuineaPigVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
