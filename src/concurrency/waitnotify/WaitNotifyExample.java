package concurrency.waitnotify;

import static concurrency.threadslesson.ColorScheme.BLUE;
import static concurrency.threadslesson.ColorScheme.RED;

public class WaitNotifyExample {
    public static void main(String[] args) {
        Message mess = new Message();

        new Thread(new Producer(mess)).start();
        new Thread(new Consumer(mess)).start();
    }

    private static class Producer implements Runnable{
        private final Message message;
        Producer(Message mess) {
            this.message = mess;
        }

        String[] text = {
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
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            for(String t : text)
            {
                synchronized (message) { //message - это lock объект
                    System.out.println(BLUE + "Producing message: " + t);
                    message.setMessage(t);
                    message.notify();
                    if (!"17".equals(t)) {
                        message.wait();
                    }
                }
                Thread.sleep(400);
            }
        }

    }

    private static class Consumer implements Runnable{
        private final Message message;
        Consumer(Message mess) {
            this.message = mess;
        }
        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            while(true)
            {
                Thread.sleep(400);
                synchronized (message)
                {
                    System.out.println(RED + "Consumming message: " + message.getMessage());
                    if (!"17".equals(message.getMessage()))
                    {
                        message.notify();
                        message.wait();
                    }
                    else return;
                }
            }
        }
    }

}
