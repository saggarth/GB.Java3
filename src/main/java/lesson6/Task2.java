package lesson6;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {

        int[] array1 = {3, 4, 1, 5, 9, 4, 4, 7, 4, 8, 6};
        int[] array2 = {4, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] array3 = {1, 1};

        System.out.println(Arrays.toString(doIt(array1)));
        System.out.println(Arrays.toString(doIt(array2)));
        System.out.println(Arrays.toString(doIt(array3)));
    }


    public static int[] doIt(int[] array) throws RuntimeException {

        int[] result;
        int i = array.length - 1;
        int j = 0;
        boolean found = false;
        while (i >= 0 && !found) {
            found = array[i] == 4;
            j = i;
            i--;
        }
        if(found) {
            result = new int[array.length - j - 1];
            for (i = j + 1; i < array.length; i++) {
                result[i - j - 1] = array[i];
            }
        } else {
            throw new RuntimeException("в массиве нет 4");
        }
        return  result;
    }
}