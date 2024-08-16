package day1;

import java.util.concurrent.locks.LockSupport;

public class PrintABC {

    private void printA(Thread t) {


        System.out.println("A");
        LockSupport.unpark(t);
    }


    private void printB(Thread t) {


        LockSupport.park();
        System.out.println("B");
        LockSupport.unpark(t);
    }

    private void printC() {


        LockSupport.park();
        System.out.println("C");
    }

    public static void main(String[] args) throws Exception {
        PrintABC p = new PrintABC();
        Thread C = new Thread(p::printC);
        Thread B = new Thread(() -> p.printB(C));
        Thread A = new Thread(() -> p.printA(B));

        A.start();
        B.start();
        C.start();


    }
}
