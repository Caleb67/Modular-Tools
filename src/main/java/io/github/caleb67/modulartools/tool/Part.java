package io.github.caleb67.modulartools.tool;

public enum Part {
    HEAD,
    ROD,
    TRIM;

    public String getName() {
        return switch (this) {
            case ROD -> "rod";
            case HEAD -> "head";
            case TRIM -> "trim";
        };
    }
}
