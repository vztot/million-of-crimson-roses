package com.vztot;

import com.vztot.impl.CallableClass;
import com.vztot.impl.ExecutorServiceCalculation;
import com.vztot.impl.ForkJoinCalculation;
import com.vztot.util.Util;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static final int THREADS = 8;
    public static final int LIST_LENGTH = 1_000_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Integer> listOfNumbers = Util.getRandomListOfNumbers(LIST_LENGTH);
        List<CallableClass> listOfCallables = Util.getListOfCallables(listOfNumbers, LIST_LENGTH,
                THREADS);

        ExecutorServiceCalculation executorServiceCalculation =
                new ExecutorServiceCalculation(listOfCallables);
        int executorSum = executorServiceCalculation.getSum();

        ForkJoinCalculation forkJoinCalculation = new ForkJoinCalculation(listOfCallables);
        int forkSum = forkJoinCalculation.getSum();

        System.out.println("Executor sum: " + executorSum + "\nFork sum: " + forkSum);
    }
}
