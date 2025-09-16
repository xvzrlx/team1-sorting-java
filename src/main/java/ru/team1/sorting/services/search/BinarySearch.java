package ru.team1.sorting.services.search;

import ru.team1.sorting.services.sorting.ClassSorting;
import ru.team1.sorting.services.sorting.SortingStrategy;

import java.util.List;

public class BinarySearch {


    public static <T, K> int search(List<T> list,
                                    K key,
                                    SortingStrategy<T> sortingStrategy,
                                    SearchStrategy<T, K> searchStrategy) {

        if (!ClassSorting.isSortedWith(sortingStrategy)){
            ClassSorting.sort(list, sortingStrategy);
        }

        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (searchStrategy.compareWithKey(list.get(mid), key) == 0) {
                return mid;
            } else if (searchStrategy.compareWithKey(list.get(mid), key) < 0) {
                start = mid + 1;
            } else  {
                end = mid - 1;
            }
        }

        return -1;
    }
}
