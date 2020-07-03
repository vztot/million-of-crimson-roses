package com.vztot.util;

import com.vztot.impl.CallableClass;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<CallableClass> getListOfCallables(
            List<Integer> listOfNumbers, int length, int threads) {
        List<CallableClass> result = new ArrayList<>();
        for (int i = 0; i < length; i = i + length / threads) {
            result.add(new CallableClass(listOfNumbers.subList(i, i + length / threads)));
        }
        return result;
    }

    public static List<Integer> getRandomListOfNumbers(int length) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add((int) (Math.random() * 10));
        }
        return result;
    }
}
