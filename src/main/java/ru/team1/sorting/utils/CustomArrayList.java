package ru.team1.sorting.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CustomArrayList<V> implements Iterable<V> {

    private static final int LENGTH_MULTIPLIER = 2;

    private int size = 10;
    private int lastIndex = 0;
    private Object[] objects = new Object[size];

    public static <T> Collector<T, ?, CustomArrayList<T>> toCustomArrayList() {
        return Collector.of(
                CustomArrayList::new,
                CustomArrayList::add,
                (left, right) -> {
                    left.addAll(
                            IntStream.range(0, right.size())
                                    .mapToObj(right::get)
                                    .toList()
                    );
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    public int size() {
        return lastIndex;
    }

    public void add(V object) {
        checkLength();
        objects[lastIndex] = object;
        lastIndex++;
    }

    public void addAll(List<V> objects) {
        objects.forEach(this::add);
    }

    public V get(int index) {
        checkIndex(index);
        return (V) objects[index];
    }

    public V remove(int index) {
        checkIndex(index);
        Object removedObject = objects[index];
        if (lastIndex != index) {
            System.arraycopy(objects, index + 1, objects, index, lastIndex - index);
        }
        objects[lastIndex] = null;
        lastIndex--;
        return (V) removedObject;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= objects.length) throw new RuntimeException("Index out of bounds.");
    }

    private void checkLength() {
        if (objects.length == lastIndex) {
            size *= LENGTH_MULTIPLIER;
            Object[] newObjects = new Object[size];
            System.arraycopy(objects, 0, newObjects, 0, lastIndex);
            objects = newObjects;
        }
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < lastIndex;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (V) objects[cursor++];
            }
        };

    }

}

