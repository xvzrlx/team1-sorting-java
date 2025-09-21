package ru.team1.sorting.services.ThreadFinder;
import lombok.SneakyThrows;
import ru.team1.sorting.model.Book;
import java.util.List;
import java.util.concurrent.*;

public class CountBooksTask {
    protected final List<Book> books;
    private final Book targetBook;

    public CountBooksTask(List<Book> books, Book targetBook) {
        this.books = books;
        this.targetBook = targetBook;
    }

    public long countOccurrences() throws InterruptedException, ExecutionException {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = books.size() / numThreads;
        int remainder = books.size() % numThreads;

        Future<Long>[] results = new Future[numThreads];

        // Назначение задач
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ?
                    startIndex + chunkSize + remainder - 1 :
                    startIndex + chunkSize - 1;

            SearchTask task = new SearchTask(books, startIndex, endIndex, targetBook);
            results[i] = (Future<Long>) executor.submit(task);
        }

        // Сбор результатов
        long totalCount = 0;
        for (Future<Long> result : results) {
            totalCount += result.get();
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        return totalCount;
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