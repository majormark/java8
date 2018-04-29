package com.major.stream.operation;

import com.major.stream.collector.PrimeNumberCollector;
import com.major.stream.model.Dish;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;


public class CollectorOperation {
    static List<Dish> dishes = Arrays.asList(
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

    public static void main(String[] args) {
        count();

        max();

        sum();

        join();

        reduce();

        group();

        partition();

        partitionPrimes(100);
    }

    public static void count() {
        dishes.stream().collect(counting());
    }

    public static void max() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCaloriesDish = dishes.stream().collect(maxBy(dishCaloriesComparator));
    }

    public static void sum() {
        int totalCalories = dishes.stream().collect(summingInt(Dish::getCalories));

        double averageCalories = dishes.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics dishesStatistics = dishes.stream().collect(summarizingInt(Dish::getCalories));
        dishesStatistics.getAverage();
        dishesStatistics.getMax();

    }

    public static void join() {
        String shortMenu = dishes.stream().map(Dish::getName).collect(joining());
        shortMenu = dishes.stream().map(Dish::getName).collect(joining(","));
    }

    public static void reduce() {
        dishes.stream().collect(reducing(0, Dish::getCalories, (c1, c2) -> c1 + c2));
    }

    public static void group() {
        Map<Dish.Type, List<Dish>> dishesByType = dishes.stream().collect(groupingBy(Dish::getType));

        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel = dishes.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <=400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                }));

        //groupingBy(用于分组的函数, 对各个分组的元素进行处理)
        //多级分组

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishByTypeCaloricLevel =
                dishes.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <=400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        })));

        //按子数组收集数据
        //每个分组的个数
        Map<Dish.Type, Long> typesCount = dishes.stream().collect(groupingBy(Dish::getType, counting()));

        //每个分组的最大值
        Map<Dish.Type, Dish> mostCaloricByType = dishes.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                                        )
                            )
        );

        //每个分组求和
        Map<Dish.Type, Integer> totalCaloricByType = dishes.stream().collect(
                groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        //mapping toCollection

    }

    public static void partition() {
        dishes.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
    }


    private static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(candidate)).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(i -> isPrime(i)));
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumberCollector());
    }

}
