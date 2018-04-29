package com.major.stream.operation;

import com.major.stream.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveValueOperation {
    public static void main(String[] args) {
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawn", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        mapToPrimitive(dishes);


        pythagoreanTriples();
    }

    public static void mapToPrimitive(List<Dish> dishes) {
        int sum = dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();

    }

    public static void boxed(List<Dish> dishes) {
        IntStream intStream = dishes.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
    }

    public static void max(List<Dish> dishes) {
        OptionalInt max = dishes.stream().mapToInt(Dish::getCalories).max();
        max.orElse(1);
    }

    public static void range() {
        IntStream.rangeClosed(1, 100);//闭区间
        IntStream.rangeClosed(1, 100);//开区间

    }

    //练习，生成0-100范围内的勾股数三元组
    public static void pythagoreanTriples() {
        Stream<int[]> pythagoreanTruples =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap( a ->
                        IntStream.rangeClosed(a, 100)
                            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)//判断是否是整数
                            .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})//IntStream -> Stream
                );

        pythagoreanTruples.limit(5)
                .forEach(t -> System.out.println(String.format("%d, %d, %d", t[0], t[1], t[2])));
    }

}
