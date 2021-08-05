package concurrency.executor;

import java.util.Random;

import static concurrency.threadslesson.ColorScheme.BLUE;
import static concurrency.threadslesson.ColorScheme.GREEN;

public class GCDRunnable extends Random implements Runnable{
    private boolean isDemon;
    public GCDRunnable(boolean isDemon) {
        this.isDemon = isDemon;
    }

    @Override
    public void run() {
        String threadType = isDemon ? "demon " : "user ";
        String threadDescription = threadType + Thread.currentThread().getName();

        System.out.println(BLUE + "Starting "+ threadDescription);
        for(int i = 0; i < 10000000; i++)
        {
            int a = nextInt();
            int b = nextInt();
            if (i % 10000 == 0)
            {
                if (!Thread.interrupted())
                {
                    int gcd = computeGCD(a, b);
                    if (gcd > 5)
                    {
                        System.out.println(GREEN + "Running in " + threadDescription + ". The GCD of " + a + " and " + b + " is " + gcd);
                    }
                }
                else
                {
                    System.out.println(BLUE + "Thread was interrupted");
                    return;
                }
            }
        }
        System.out.println(BLUE + "Leaving the thread "+ threadDescription);
    }

    private int computeGCD(int number1, int number2) //находит самый большой делитель двух чисел
    {
        if (number2 == 0) return number1;
        else
        {
            return computeGCD(number2, number1 % number2);
        }
    }

    //50 % 30 = 20
    //30 % 20 = 10
    //20 % 10 = 0
    //10 % 0 -> return 10



}
