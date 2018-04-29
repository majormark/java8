package com.major.lambda.model;

public class Apple {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int weight;
    public Apple() {}

    public Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}
