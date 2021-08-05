package concurrency.synch;

import java.util.ArrayList;
import java.util.List;

public class SychronizeBlockes {
    private int[] a = {0,1,2,3,4,5,6,7,8,9,10};
    private int[] b = {0,11,12,13,14,15,16,17,18,19,20};
    private List<Integer> intList1 = new ArrayList<>();
    private List<Integer> intList2 = new ArrayList<>();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public static void main(String[] args) {
        SychronizeBlockes sb = new SychronizeBlockes();
        sb.copy();
    }

    private void copy()
    {
        long start = System.currentTimeMillis();

        Thread th1 = new Thread(new RunnerA());
        Thread th2 = new Thread(new RunnerB());
        th1.start();
        th2.start();

        try { // если не сделать join - будет не правильно считаться время. Это если методы copyArrayA и copyArrayB synchronized
            th1.join(); //приостанавливает выполнение текущего потока до тех пор, пока не завершится другой поток
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - start) + "ms");
    }

    //private synchronized void copyArrayA() // недостаток - synchronized -  не работает прааллельно
    private void copyArrayA() // недостаток - synchronized -  не работает прааллельно
    {
        synchronized (lock1) { // - это синхронайзд блок, для решения вышеописанной проблемы,
                               // чтобы не блокировал параллельное выполнение метода copyArrayB
            for(int i = 0; i < a.length; i++)
            {
                intList1.add(a[i]);
                System.out.println(intList1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //private synchronized void copyArrayB()
    private void copyArrayB()
    {
        synchronized (lock2) {
            for (int i = 0; i < b.length; i++) {
                intList2.add(b[i]);
                System.out.println(intList2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RunnerA implements Runnable{
        @Override
        public void run() {
            copyArrayA();
        }
    }

    private class RunnerB implements Runnable{
        @Override
        public void run() {
            copyArrayB();
        }
    }

}
