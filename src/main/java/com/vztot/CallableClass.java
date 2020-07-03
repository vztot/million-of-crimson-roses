package com.vztot;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableClass implements Callable<Integer> {
    private List<Integer> listInt;

    public CallableClass(List<Integer> listInt) {
        this.listInt = listInt;
    }

    @Override
    public Integer call() {
        return listInt
                .stream()
                .reduce(0, Integer::sum);
    }
}
