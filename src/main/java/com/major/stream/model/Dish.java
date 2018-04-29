package com.major.stream.model;

import lombok.Data;

@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type { MEAT, FISH, OTHER }

    public enum CaloricLevel { DIET, NORMAL, FAT}

    @Override
    public String toString() {
        return name;
    }
}
