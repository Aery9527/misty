package org.misty.util.verify;

public class ExamineIntervals {

    public enum Floor {
        INCLUDE("["),
        EXCLUDE("(");
        private final String symbol;

        Floor(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public enum Ceiling {
        INCLUDE("]"),
        EXCLUDE(")");
        private final String symbol;

        Ceiling(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

}
