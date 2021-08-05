package concurrency.testConc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RunnableDemo2 implements Runnable{
    static int Count = 0;
    private Thread t;
    private String threadName;

    RunnableDemo2( String name) {
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
                Thread.sleep(15000);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток      " +  threadName + " прерван.");
        }
        System.out.println("Поток      " +  threadName + " завершается." + "  co="+ Count);
    }

    public void start () {
        System.out.println("Запуск " +  threadName );
        if (t == null) {
            t = new Thread ((Runnable) this, threadName);
            t.start ();
        }
    }
}
