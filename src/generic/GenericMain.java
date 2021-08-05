package generic;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericMain {
    public static void main(String[] args)
    {
        Container<Integer> box = new Container<>(1);

        Container<String> box2 =  new Container("1");

        System.out.println((Integer) box.getObject1()*2);

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] intArray = {1,2,3,4,5,6,7,8,9};
        Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5};
        String[] stringArray = {"1_","2_","3_","4_","5_"};

        printArray(intArray);
        printArray(doubleArray);
        printArray(stringArray);

        System.out.println("max Integers" + findMax(intArray));
        System.out.println("max Double" + findMax(doubleArray));
        //System.out.println("max String" + findMax(stringArray)); //в алфавитном порядке

        Number n = Integer.valueOf(20);

        List<Number> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        printElements(list2);

        /*List<Integer> list1 = Arrays.asList(intArray);
        printElements(list1); //так ошибка
        */
        printElements(Arrays.asList(intArray));

        MyBox<Integer> myBox = new MyBox<Integer>("");

        //Double aDouble = MyBox.<Double>returnValue(0.03);
        Double aDouble = MyBox.returnValue(0.03); // можно не указывать double  в таком случае


    }

    private static void printElements(List<Number> list)//преобразовывает в нужный тип листа
    {

        for(Number n: list)
        {
            System.out.print(n + " ");
        }
        System.out.println();
    }


    private static <T> void printArray(T[] Array)
    {
        System.out.println("generic ");
        for(T elem : Array)
        {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    //private static <T extends Comparable<T>> T findMax(T[] Array) //это верхняя граница для типа
                                                                   // можно использовать > 1 типа, но только 1 класс и несколько интерфейсов,
                                                                   // тут всегда применяется слово extends
                                                                   // между классом и интерфейсами ставятся &, если есть класс, то он всегда первый
    private static <T extends Number & Comparable<T>> T findMax(T[] Array) // например, огрничим тип классом Number - в таком случае ошибка для String,
                                                                           // тк Number - родитель для Integer и Double
    {
        System.out.println("generic ");
        T max = Array[0];

        for(T e : Array)
        {
            if(e.compareTo(max)>0) max = e;
        }
        return max;
    }

    /*private static void printArray(Integer[] intArray)
    {
        System.out.println("non generic ");
        for(Integer elem : intArray)
        {
            System.out.println(elem + " ");
        }
        System.out.println();
    }

    private static void printArray(Double[] intArray)
    {
        System.out.println("non generic ");
        for(Double elem : intArray)
        {
            System.out.println(elem + " ");
        }
        System.out.println();
    }

    private static void printArray(String[] intArray)
    {
        System.out.println("non generic ");
        for(String elem : intArray)
        {
            System.out.println(elem + " ");
        }
        System.out.println();
    }*/
}
