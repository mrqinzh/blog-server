package chat;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                a.add();
            }).start();

            new Thread(() -> {
                a.decrease();
            }).start();
            Thread.sleep(100);
        }
    }

}
class A {

    private int num = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            while (num != 0) {
                condition.wait();
            }
            num++;
            System.out.println(num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            while (num != 1) {
                condition.wait();
            }
            num--;
            System.out.println(num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


