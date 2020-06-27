package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class task2 {
    public static void main(String[] args) {
        String[] array1 = {"0", "1", "2"};
        Integer[] array2 = {0, 1, 2};
        toArrayList(array1);
        toArrayList(array2);

    }
    public static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<T>(Arrays.asList(array));
    }
}
