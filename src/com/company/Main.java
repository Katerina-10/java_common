package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            doEverything();
        } catch (InvalidInputParamException e) {
            System.out.println("InvalidInputParamException");
            e.printStackTrace();
        }
        //doEverything();
        /*try {
            doEverything();
        } catch (NullPointerException e) {
            System.out.println("npe");
            //e.printStackTrace();
            Throwable[] supp =  e.getSuppressed();
            System.out.println(supp[0]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("array exeption");
        }*/
    }

    private static void doEverything()
    {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        PrintWriter wr = null;
        do {
            try {
                System.out.println("a");
                int a = scanner.nextInt();
                System.out.println("b");
                int b = scanner.nextInt();

                //int[] arr = new int[1];
                //int i = arr[2];
                wr = new PrintWriter(new FileWriter("out.txx"));
                continueLoop = false;
            }
            catch (IOException e)
            {
                System.out.println("not open file");
                e.printStackTrace();
            }
            catch(IndexOutOfBoundsException e)
            {
                throw new InvalidInputParamException("index out of bound.thrown in doEverything "+ e);
            }
            catch(Exception e)
            {
                System.out.println("all exceptions");
                e.printStackTrace();
            }
            finally {
                System.out.println("finally block");
                if (wr!=null) wr.close();
            }
        } while(continueLoop);
    }
}
