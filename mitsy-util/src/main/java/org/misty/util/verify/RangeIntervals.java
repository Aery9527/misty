package org.misty.util.verify;

public enum RangeIntervals {
    INCLUDE_INCLUDE("[", "]"),
    INCLUDE_EXCLUDE("[", ")"),
    EXCLUDE_INCLUDE("(", "]"),
    EXCLUDE_EXCLUDE("(", ")");

    private final String floorSymbol;

    private final String ceilingSymbol;

    RangeIntervals(String floorSymbol, String ceilingSymbol) {
        this.floorSymbol = floorSymbol;
        this.ceilingSymbol = ceilingSymbol;
    }

    public boolean isFloorInclude() {
        return "[".equals(this.floorSymbol);
    }

    public boolean isFloorExclude() {
        return "(".equals(this.floorSymbol);
    }

    public boolean isCeilingInclude() {
        return "]".equals(this.floorSymbol);
    }

    public boolean isCeilingExclude() {
        return ")".equals(this.floorSymbol);
    }

    public String getFloorSymbol() {
        return floorSymbol;
    }

    public String getCeilingSymbol() {
        return ceilingSymbol;
    }
}
