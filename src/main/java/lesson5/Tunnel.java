package lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore = new Semaphore(MainClass.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + this.getDescription());
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + this.getDescription());
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + this.getDescription());
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}