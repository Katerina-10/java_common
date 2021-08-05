package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static concurrency.threadslesson.ColorScheme.RED;

public class Launcher {
    private static final int POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        boolean isDemon = true;
        System.out.println(RED+ "Starting main thread");
        GCDRunnable r = new GCDRunnable(isDemon);
        //runInOneThread(r, isDemon);
        //Thread.sleep(100);
        runWithExecutors(r, isDemon);
        System.out.println(RED+"Leaving the main thread");

    }

    private static void runInOneThread(GCDRunnable r, boolean isDemon) throws InterruptedException {
        Thread th1 = new Thread(r);
        //th1.setDaemon(true); // завершается тогда, когда завершается main thread
        if (isDemon)
        {
            th1.setDaemon(true);
        }
        th1.start();
        Thread.sleep(100);
        th1.interrupt();
    }

    private static void runWithExecutors(GCDRunnable r, boolean isDemon) throws InterruptedException
    {
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread th = new Thread(r);
                if (isDemon)
                    th.setDaemon(true);
                return th;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE, factory); //для нескольких потоков
        for (int i = 0; i < 5; i++)
        {
            System.out.println(i);
            executorService.execute(r);
        }
        executorService.shutdown();//потоки больше не принимают новых заданий, иначе потоки не завершаются и ждут заданий -> программа не завершается

        executorService.awaitTermination(30, TimeUnit.SECONDS); // если потоки не закончили свою работу за 30 секунд -> завершать работу принудительно


    }
}
