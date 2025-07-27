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
    ACORN_SQUASH(9); // Easter egg variant

    // Array of variants sorted by ID for quick lookup
    private static final GuineaPigVariant[] BY_ID = Arrays.stream(values())
            .sorted(Comparator.comparingInt(GuineaPigVariant::getId))
            .toArray(GuineaPigVariant[]::new);

    private final int id;

    GuineaPigVariant(int id) {
        this.id = id;
    }

    // Returns the integer ID for serialization/tracking
    public int getId() {
        return this.id;
    }

    /**
     * Gets a variant by ID with modulo fallback to ensure the value is always in range.
     * @param id The ID to look up.
     * @return A valid GuineaPigVariant, defaults to looping around using modulo if out-of-bounds.
     */
    public static GuineaPigVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
