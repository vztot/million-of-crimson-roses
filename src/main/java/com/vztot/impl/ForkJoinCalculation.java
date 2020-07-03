package com.vztot.impl;

import com.vztot.Main;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinCalculation {
    private ForkJoinPool forkJoinPool;
    private List<CallableClass> listOfCallables;

    public ForkJoinCalculation(List<CallableClass> listOfCallables) throws ExecutionException,
            InterruptedException {
        this.forkJoinPool = new ForkJoinPool(Main.THREADS);
        this.listOfCallables = listOfCallables;
    }

    public int getSum() throws ExecutionException, InterruptedException {
        int sum = 0;
        for (Future<Integer> future : forkJoinPool.invokeAll(listOfCallables)) {
            sum += future.get();
        }
        return sum;
    }
}
