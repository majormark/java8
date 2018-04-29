package com.major.stream.operation;

import com.major.stream.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class BasicOperation {

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

        distinction();
        System.out.println();

        limit(dishes, 3);
        System.out.println();

        skip(dishes, 2);
        System.out.println();

        map(dishes);

        flatMap(dishes);

        match(dishes);

        find(dishes);

        reduce(dishes);//count //最大 //最小 求和


    }

    public static void distinction() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        System.out.println("distinct前");
        numbers.forEach(System.out::print);

        System.out.println("\ndistinct后");

        numbers.stream()
                .filter(i -> (i.intValue() & 1) == 0)
                .distinct()
                .forEach(System.out::print);

        System.out.println();
    }

    public static void limit(List<Dish> dishes, int limit) {
        System.out.println("dishes limit 操作 limit个数: " + limit);

        List<Dish> result = dishes.stream()
                .limit(limit)
                .collect(toList());

        result.forEach(dish -> System.out.print(dish.getName() + " "));
        System.out.println();
    }

    public static void skip(List<Dish> dishes, int skip) {
        System.out.println("dishes skip 操作 skip: " + skip);

        List<Dish> result = dishes.stream()
                .skip(skip)
                .collect(toList());

        result.forEach(dish -> System.out.print(dish.getName() + " "));
        System.out.println();
    }

    public static void map(List<Dish> dishes) {
        dishes.stream().map(Dish::getName).collect(toList());
    }


    public static void flatMap(List<Dish> dishes) {
        dishes.stream()
                .map(Dish::getName)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(toList());

    }

    public static void match(List<Dish> dishes) {
        // 任意一个元素是蔬菜 匹配表达式
        boolean hasVegetarian = dishes.stream().anyMatch(Dish::isVegetarian);

        //所有元素匹配表达式
        boolean isAllVegetarian = dishes.stream().allMatch(Dish::isVegetarian);

        //没有一个元素匹配表达式
        boolean noVegetarian = dishes.stream().noneMatch(Dish::isVegetarian);
    }

    public static void find(List<Dish> dishes) {
        dishes.stream() //findAny 并行效果更好
                .filter(Dish::isVegetarian)
                .findAny() //return 任意一个
                .ifPresent(d -> System.out.println(d.getName()));//Optional 操作

        List<Integer> someNumber = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumber.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();

    }

    public static void reduce(List<Dish> dishes) {
        //reduce 想要并行执行， 操作之间是满足结合律的

        //求和
        List<Integer> someNumber = Arrays.asList(1, 2, 3, 4, 5);
        int sum = someNumber.stream()
                .reduce(0, (a, b) -> a + b);
        sum = someNumber.stream()
                .reduce(0, Integer::sum);

        //最小值
        Optional<Integer> min = someNumber.stream()
                .reduce(Integer::min);
        someNumber.stream()
                .min(Integer::compareTo);

        //count
        int count = dishes.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        long count1 = dishes.stream().count();


    }

}
