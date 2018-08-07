package com.reserv.incubator.model;

public enum Ranges {
    SECOND(1),
    MINUTE(2),
    HOUR(3),
    DAY(4);

    private final int value;
    Ranges(int i) {
        this.value = i;
    }

    public static Ranges fromValue(int value)
            throws IllegalArgumentException {
        try {
            for(Ranges e : Ranges.values()){
                if(value == e.value)
                    return e;
            }
            return null;
        } catch(Exception e) {
            throw new IllegalArgumentException("Unknown enum value :"+ value);
        }
    }

    public static Ranges getByName(String name) {
        return Ranges.valueOf(name);
    }
}
