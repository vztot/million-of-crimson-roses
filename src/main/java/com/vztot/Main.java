package com.vztot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
    private static final int THREADS = 8;
    private static final int LIST_LENGTH = 1_000_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Integer> listOfNumbers = getRandomListOfNumbers();
        List<CallableClass> listOfCallables = getListOfCallables(listOfNumbers);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        int sumExecutor = 0;
        for (Future<Integer> future : executorService.invokeAll(listOfCallables)) {
            sumExecutor += future.get();
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREADS);
        int sumJoin = 0;
        for (Future<Integer> future : forkJoinPool.invokeAll(listOfCallables)) {
            sumJoin += future.get();
        }

        System.out.println("Executor sum: " + sumExecutor + "\nFork sum: " + sumJoin);
    }

    private static List<CallableClass> getListOfCallables(List<Integer> listOfNumbers) {
        List<CallableClass> result = new ArrayList<>();
        for (int i = 0; i < LIST_LENGTH; i = i + LIST_LENGTH / THREADS) {
            result.add(new CallableClass(listOfNumbers.subList(i, i + LIST_LENGTH / THREADS)));
        }
        return result;
    }

    private static List<Integer> getRandomListOfNumbers() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < LIST_LENGTH; i++) {
            result.add((int) (Math.random() * 10));
        }
        return result;
    }
}
