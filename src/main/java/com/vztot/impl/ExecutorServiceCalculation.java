package com.vztot.impl;

import com.vztot.Main;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCalculation {
    private ExecutorService executorService;
    private List<CallableClass> listOfCallables;

    public ExecutorServiceCalculation(List<CallableClass> listOfCallables) {
        this.executorService = Executors.newFixedThreadPool(Main.THREADS);
        this.listOfCallables = listOfCallables;
    }

    public int getSum() throws InterruptedException, ExecutionException {
        int sum = 0;
        for (Future<Integer> future : executorService.invokeAll(listOfCallables)) {
            sum += future.get();
        }
        return sum;
    }
}
