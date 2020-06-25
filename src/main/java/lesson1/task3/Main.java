package lesson1.task3;

public class Main {
    public static void main(String[] args) {
        Box<Apple> boxA = new Box<Apple>();
        Box<Orange> boxO = new Box<Orange>();
        Box<Fruit> boxF = new Box<Fruit>();

        for (int i = 0; i < 5; i++) {
            Apple apple = new Apple();
            boxA.add(apple);
        }

        for (int i = 0; i < 5; i++) {
            Orange orange = new Orange();
            boxO.add(orange);
        }

        System.out.println(boxA.getWeight());
        System.out.println(boxO.getWeight());
        System.out.println(boxA.compare(boxO));

        boxA.transfer(boxF);
        System.out.println(boxA.getWeight());
        System.out.println(boxF.getWeight());
    }
}