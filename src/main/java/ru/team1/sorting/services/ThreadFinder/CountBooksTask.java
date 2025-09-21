package ru.team1.sorting.services.ThreadFinder;
import lombok.SneakyThrows;
import ru.team1.sorting.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CountBooksTask {
    protected final List<Book> books;
    private final Book targetBook;

    public CountBooksTask(List<Book> books, Book targetBook) {
        this.books = books;
        this.targetBook = targetBook;
    }

    public static int countOccurrences(List<Book> books, Book targetBook){

        // Число потоков для распараллеливания
        int numThreads = Runtime.getRuntime().availableProcessors();
        int chunkSize = books.size() / numThreads;

        // Создаем списки для сохранения результатов
        List<CompletableFuture<Integer>> futures = new ArrayList<>(numThreads);

        // Создаем задания для каждого потока
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? books.size() : ((i + 1) * chunkSize);

            // Копируем нужную часть списка, создавая полноценный ArrayList
            ArrayList<Book> sublistCopy = new ArrayList<>(books.subList(start, end));

            // Передаем копию подсписка в задание
            futures.add(CompletableFuture.supplyAsync(() -> countOccurrences(targetBook, sublistCopy)));
        }

        // Объединяем результаты выполнения потоков
        int totalCount = 0;
        for (CompletableFuture<Integer> future : futures) {
            totalCount += future.join();
        }

        System.out.println("Итого найдено экземпляров: " + totalCount);
        return totalCount;
    }

    private static int countOccurrences(Book targetBook, ArrayList<Book> sublist) {
        int count = 0;
        for (Book book : sublist) {
            if (book.equals(targetBook)) {
                count++;
            }
        }
        return count;
    }


    private static class SearchTask implements Runnable {
        private final List<Book> books;
        private final int startIndex;
        private final int endIndex;
        private final Book targetBook;
        private volatile long localCount;

        public SearchTask(List<Book> books, int startIndex, int endIndex, Book targetBook) {
            this.books = books;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.targetBook = targetBook;
            this.localCount = 0;
        }

        @Override
        public void run() {
            synchronized (this) {
                for (int i = startIndex; i <= endIndex; i++) {
                    if (targetBook.equals(books.get(i))) {
                        localCount++;
                    }
                }
            }
        }

        public long getLocalCount() {
            return localCount;
        }
    }
}