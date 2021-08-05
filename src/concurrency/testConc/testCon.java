package concurrency.testConc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testCon {

    public static void main(String[] args) throws InterruptedException {
        RunnableDemo2 R1 = new RunnableDemo2( "Поток-1");
        R1.start();
//        R1.run();

        RunnableDemo2 R2 = new RunnableDemo2( "Поток-2");
        R2.start();
//        R2.run();

        RunnableDemo R3 = new RunnableDemo( "Поток-3");
        R3.start();
//        R3.run();

        RunnableDemo R4 = new RunnableDemo( "Поток-4");
        R4.start();
//        R4.run();

        RunnableDemo R5 = new RunnableDemo( "Поток-5");
        R5.start();
//        R5.run();


    }
}

class RunnableDemo implements Runnable {
    static int Count = 0;
    private Thread t;
    private String threadName;

    RunnableDemo( String name) {
        threadName = name;
        Count++;
        System.out.println("Создание   " +  threadName );
    }

    public void run() {
        System.out.println("Выполнение " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(formater.format(date));
                System.out.println("Поток:     " + threadName + ", " + i);
                i++;
                // Пусть поток немного подождет.
                //Thread.sleep(15000);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток      " +  threadName + " прерван.");
        }
        System.out.println("Поток      " +  threadName + " завершается." + "  co="+ Count);
    }

    public void start () {
        System.out.println("Запуск " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
