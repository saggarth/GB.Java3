package lesson6;

public class Task3 {

    public static void main(String[] args) {
        int[] array1 = {3, 4, 1, 5, 9, 4, 4, 7, 4, 8, 6};
        int[] array2 = {4, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] array3 = {2, 3};

        System.out.println(doIt(array1));
        System.out.println(doIt(array2));
        System.out.println(doIt(array3));
    }

    public static boolean doIt(int[] array) {
        boolean result = true;
        int count1 = 0;
        int count4 = 0;

        for (int a : array) {
            if (a == 1) {
                count1++;
            } else {
                if (a == 4) {
                    count4++;
                } else {
                    result = false;
                }
            }
        }
        return result || count1 > 0 || count4 > 0;
    }
}