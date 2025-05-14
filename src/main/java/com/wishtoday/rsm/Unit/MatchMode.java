package com.wishtoday.rsm.Unit;

public enum MatchMode {
    CONTAINS() {
        @Override
        public boolean matches(String s, String p) {
            return s.contains(p) && !s.isEmpty();
        }
    },
    PRECISION() {
        @Override
        public boolean matches(String s, String p) {
            return s.equals(p) && !s.isEmpty();
        }
    };

    public abstract boolean matches(String s, String p);

    public boolean equals(MatchMode mode) {
        return this == mode;
    }
}
