package lesson1;

import java.util.Arrays;

public class task1 {
    public static void main(String[] args) {

        Integer[] array1 = {0, 1};
        String[] array2 = {"0", "1", "2", "4", "5"};

        System.out.println(Arrays.deepToString(array1));
        swap(array1, 0, 1);
        System.out.println(Arrays.deepToString(array1));

        System.out.println(Arrays.deepToString(array2));
        swap(array2, 2, 4);
        System.out.println(Arrays.deepToString(array2));
    }

    public static void swap(Object[] arr, int index1, int index2) {
        Object tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}