package ru.team1.sorting.model;

public class Book {
    private final String title;
    private final int pages;
    private final int year;

    private Book(Builder builder) {
        this.title = builder.title;
        this.pages = builder.pages;
        this.year = builder.year;
    }

    public static class Builder {
        private String title = "Неизвестно";
        private int pages = 1;
        private int year = 2024;

        public Builder title(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Заголовок не может быть null или пустым");
            }
            this.title = title.trim();
            return this;
        }

        public Builder pages(int pages) {
            if (pages <= 0) {
                throw new IllegalArgumentException("Страницы должны быть > 0");
            }
            this.pages = pages;
            return this;
        }

        public Builder year(int year) {
            if (year < 0 || year > 2025) {
                throw new IllegalArgumentException("Год должен быть от 0 до 2025");
            }
            this.year = year;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getTitle() { return title; }
    public int getPages() { return pages; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Заголовок: '" + title + "', Страниц: " + pages + ", Год: " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + pages;
        result = 31 * result + year;
        return result;
    }


}
