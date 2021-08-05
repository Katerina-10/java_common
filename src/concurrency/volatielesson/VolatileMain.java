package concurrency.volatielesson;

import static concurrency.threadslesson.ColorScheme.GREEN;
import static concurrency.threadslesson.ColorScheme.RED;

public class VolatileMain {
    private static volatile int counter; //volatile - значит, что переменная будет изменяться в потоке каком-то
    private static int x;
    private static int y;

    public static void main(String[] args)
    {
        new SimpleWriter().start();
        new SimpeReader().start();
    }

    private static void update() //если изменять все переменные класса в одном методе, то они будут видны для всех потоков,
                                // даже если volatile одна переменная
    {
        counter++;
        x++;
        y++;
    }

    private static class SimpleWriter extends Thread{
        @Override
        public void run() {
            int localCounter = counter;
            for (int i = 0; i < 10; i++)
            {
                System.out.println(GREEN+ "wtiter increments counter " + (localCounter + 1));
                counter = ++localCounter;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SimpeReader extends Thread{
        @Override
        public void run() {
            int localCounter = counter;
            while (localCounter < 10)
            {
                if (localCounter != counter)
                {
                    System.out.println(RED + "Reader reads counter " + counter);
                    localCounter = counter;
                }
            }
        }
    }
}
