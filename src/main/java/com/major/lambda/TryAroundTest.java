package com.major.lambda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TryAroundTest {

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();
        list.add("a");
        String filepath = TryAroundTest.class.getClassLoader().getResource("test.txt").getPath();
        System.out.println("path: " + filepath);
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String result = br.readLine();
            System.out.println(result);
        }
    }
}
