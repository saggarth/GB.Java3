package lesson4;

public class task1 {
    static volatile char letter = 'A';

    public static void main(String[] args) {
        Object lock = new Object();
        class MyThread implements Runnable {
            private final char letterOld;
            private final char letterNew;
            public MyThread(char letterOld, char letterNew) {
                this.letterOld = letterOld;
                this.letterNew = letterNew;
            }
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (letter != letterOld){
                                lock.wait();
                            }
                            System.out.print(letterOld);
                            letter = letterNew;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        new Thread(new MyThread('A', 'B')).start();
        new Thread(new MyThread('B', 'C')).start();
        new Thread(new MyThread('C', 'A')).start();
    }
}