package concurrency.threadslesson;

import static concurrency.threadslesson.ColorScheme.*;

public class ConcurentMain {
    public static void main(String[] args)
    {
        SimpeThread th1 = new SimpeThread();
        th1.start();

        SimpeThread th2 = new SimpeThread();
        th2.start();

        th2.interrupt();

        Thread th3 = new Thread(new SimpleRunner());
        th3.start();

        /*Thread th4 = new Thread(()->{
            System.out.println("Hello from lyambda Runnable");
        });
        th4.start();*/

        new Thread(()->{
            System.out.println("Hello from lyambda Runnable");
        }).start();


        System.out.println("hello from main");

    }
}

class SimpeThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                Thread.sleep(1000); //1 sek

            } catch (InterruptedException e) {
                System.out.println(YELLOW + "WARNING: "+ currentThread().getName() + " was interapted - ") ;
            }
            System.out.println(GREEN + "INFO: "+ currentThread().getName() + " - " + i ) ;
        }
        if (Thread.interrupted())
        {
            return;
        }
    }
}

class SimpleRunner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                Thread.sleep(1000); //1 sek
            } catch (InterruptedException e) {
                System.out.println(YELLOW + "WARNING - Runnable: "+ Thread.currentThread().getName() + " was interapted - ") ;
            }
            System.out.println(GREEN + "INFO - Runnable : "+ Thread.currentThread().getName() + " - " + i ) ;
        }
    }
}