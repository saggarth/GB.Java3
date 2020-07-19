package lesson7;

public class TestClass {
    public static void method1() {
        System.out.println("Метод - 1");
    }

    @BeforeSuite
    public static void method2() {
        System.out.println("Метод - 2, before");
    }

    @Test(priority = 6)
    public static void method3() {
        System.out.println("Метод - 3, приоритет - 6");
    }

    @Test(priority = 7)
    public static void method4() {
        System.out.println("Метод - 4, приоритет - 7");
    }

    @Test
    public static void method5() {
        System.out.println("Метод - 5, приоритет - 5");
    }

    @Test
    public static void method6() {
        System.out.println("Метод - 6, приоритет - 5");
    }

    @Test(priority = 8)
    public static void method7() {
        System.out.println("Метод - 7, приоритет - 8");
    }

    @AfterSuite
    public static void method8() {
        System.out.println("Метод - 8, after");
    }
}