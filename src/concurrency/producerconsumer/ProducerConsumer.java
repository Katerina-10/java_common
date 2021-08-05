package concurrency.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static concurrency.threadslesson.ColorScheme.GREEN;
import static concurrency.threadslesson.ColorScheme.RED;

public class ProducerConsumer {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5); // 5 - max size. Если больше пяти - блокируется очередь
    //эта коллекция заменяет sinchronized

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();

    }

    private static class Producer implements Runnable{

        String[] message = {
                "111111",
                "22222222",
                "3333333",
                "444444444",
                "55555",
                "66666",
                "7777777777",
                "8888888",
                "999999999",
                "10000000",
                "11-11-11-11",
                "12-12-12-12",
                "13-13-13",
                "14-14-14-14",
                "15-15-15-15",
                "16-16-16",
                "17"
        };

        @Override
        public void run() {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void producer() throws InterruptedException {
            Random r = new Random();
            for (int i = 0; i < message.length; i++)
            {
                queue.put(message[i]); // если в очереди уже есть 5 элементов, то этот метод ждет, пока consumer прочитает одну запись, т.е. освободит одно место
                System.out.println(GREEN + "Producing " + message[i] + ". Queue size is " + queue.size());
                Thread.sleep(r.nextInt(2000));//3 sek
            }
        }
    }

    private static class Consumer implements Runnable{


        @Override
        public void run() {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consumer() throws InterruptedException {
            Random r = new Random();
            while(true){
                String message = queue.take();
                System.out.println(RED + "Consuming "+ message + ". Queue size is " + queue.size());
                if (!"17".equals(message))
                {
                    Thread.sleep(r.nextInt(3000));//3 sek
                }
                else return;
            }
        }
    }

}
