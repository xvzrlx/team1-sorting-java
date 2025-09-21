package ru.team1.sorting.services.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EvenOnlySorting {
    public static <T> void sort(List<T> list, EvenStrategy<T> strategy){
        List<T> evenList = list.stream().filter(strategy::checkEven).collect(Collectors.toCollection(ArrayList::new));
        ClassSorting.sort(evenList, strategy);

        for(int i = 0; i < list.size(); i++){
            if(strategy.checkEven(list.get(i))){
                list.set(i, evenList.removeFirst());
            }
        }
    }
}
