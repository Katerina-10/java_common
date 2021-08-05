package concurrency.locks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static concurrency.threadslesson.ColorScheme.RED;
import static concurrency.threadslesson.ColorScheme.GREEN;

public class WaitingQueue {
    public static void main(String[] args) {
        int operators = 5;
        int customers = 21;

        SemaphoredServiceDesk serviceDesk = new SemaphoredServiceDesk(operators, customers);
        ExecutorService executorService = Executors.newCachedThreadPool();


        IntStream.range(0,customers).
                forEach(client -> executorService.submit(()->{
                    serviceDesk.connect();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(GREEN + "Number of conected customers " + serviceDesk.getCustomersConnected());
                    System.out.println(RED + "Number of customers in a queue " + serviceDesk.getCustomersWaiting());
                }));//их будет 20, тк последний не включается в этом методе
        
        executorService.shutdown();
        //executorService.awaitTermination(30, TimeUnit.SECONDS);

    }

    private static class SemaphoredServiceDesk{
        private AtomicInteger connectedCustomers = new AtomicInteger();//без параметра инициализируется 0
        private AtomicInteger customersQueued;

        private Semaphore semaphore;

        public SemaphoredServiceDesk(int operatorNum, int customerNum)
        {
            semaphore = new Semaphore(operatorNum);//кол-во пермитов, которые могут получить доступ в один момнт времени
            customersQueued = new AtomicInteger(customerNum);
        }

        public void connect()
        {
            Random random = new Random();
            try {
                semaphore.acquire();
                connectedCustomers.incrementAndGet();
                customersQueued.decrementAndGet();
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                disconnect();
            }
        }

        private void disconnect() {
            semaphore.release();
            connectedCustomers.decrementAndGet();
        }

        public int getCustomersConnected() {
            return connectedCustomers.get();
        }
        

        private int getCustomersWaiting()
        {
            return customersQueued.get();
        }
    }
}
