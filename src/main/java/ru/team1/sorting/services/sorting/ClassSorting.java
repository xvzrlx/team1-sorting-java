package ru.team1.sorting.services.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClassSorting {
    private final static int THREADS = 4;
    private static final Queue<Future<?>> futures = new ConcurrentLinkedQueue<>();

    private static SortingStrategy<?> lastStrategy = null;
    private static boolean isSorted = false;

    public static <T> void sort(List<T> list, SortingStrategy<T> strategy) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);


        futures.clear();
        futures.add(executor.submit(() -> quickSort(list, 0, list.size() - 1, strategy, executor)));

        try {
            while (!futures.isEmpty()) {
                Future<?> future = futures.poll();
                if (future != null) {
                    future.get();
                }
            }
            lastStrategy = strategy;
            isSorted = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static boolean isSortedWith(SortingStrategy<?> strategy) {
        return isSorted && lastStrategy.getClass().equals(strategy.getClass());
    }

    private static <T> void quickSort(List<T> list, int start, int end, SortingStrategy<T> strategy) {
        if (end <= start) return;

        int pivot = portion(list, start, end, strategy);

        futures.add(executor.submit(() -> quickSort(list, start, pivot - 1, strategy, executor)));
        futures.add(executor.submit(() -> quickSort(list, pivot + 1, end, strategy, executor)));

    }

    private static <T> int portion(List<T> list, int start, int end, SortingStrategy<T> strategy) {
        T pivot = list.get(end);
        int i = start - 1;

        for (int j = start; j <= end - 1; j++) {
            if (strategy.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        i++;
        swap(list, i, end);

        return i;
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
