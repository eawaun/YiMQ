package com.yimq.common;

import java.io.FileNotFoundException;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Random r = new Random();
        System.out.println(Math.abs(r.nextInt() % 999));
    }
}
