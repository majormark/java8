package com.major.lambda;

import com.major.lambda.model.Apple;

import java.util.ArrayList;
import java.util.List;
import static java.util.Comparator.comparing;

public class CompareTest {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("app12", 10));
        inventory.add(new Apple("app22", 21));
        inventory.add(new Apple("app33", 14));

        inventory.sort(comparing( a -> a.getWeight()));

        printApple(inventory);

        inventory.sort(comparing( Apple::getName));

        System.out.println("----------------");
        printApple(inventory);

        System.out.println("----------------");
        System.out.println("比较器复合");
        inventory.add(new Apple("app1", 14));
        inventory.add(new Apple("app4", 14));
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getName)); //按重量递减，如果两个苹果一样重，按照名字排序

        printApple(inventory);



    }

    public static void printApple(List<Apple> inve) {
        inve.forEach( a -> System.out.println(a.name + ": " + a.weight));
    }
}
