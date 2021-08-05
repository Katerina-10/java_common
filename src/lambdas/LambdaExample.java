package lambdas;

import lambdas.model.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@FunctionalInterface
interface ElementProcessor<T extends Number>
{
    //double Process(int element, int elem2);
    double Process(T element);
}

@FunctionalInterface
interface ExecutiveFunction
{
    void Process();

    // закоментировать для первого примера с TimeUtil
    public static void measure(ExecutiveFunction func){
        long start = System.currentTimeMillis();
        func.Process();
        long end = System.currentTimeMillis();
        System.out.println("Time spent " + (end-start));
    }

    default ExecutiveFunction combinerExecutiveFunction(ExecutiveFunction that)
    {
        return () -> {
            Process();
            that.Process();
        };

    }
}

public class LambdaExample {
    public static void main(String[] args)
    {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(6.4);
        doubleList.add(8.6);
        doubleList.add(1.23);
        doubleList.add(4.13);
        doubleList.add(12.2);

        //processElements(intList, 4, (x, y) -> Math.pow(x, y));
        //processElements(intList, 4, (x, y) -> multiply(x, y)); //в х передается число из списка, в у передается 4

        System.out.println(intList);
        processElements(intList, x -> Math.sin(x.doubleValue()));
        //processElements(intList, x ->  Math.pow(x.doubleValue(), (double) 4) );
        //System.out.println(doubleList);
        processElements(doubleList, d -> Math.sin(d.doubleValue()));

        // для реализации TimeUtil - расскомментировать
        //TimeUtil.measure(()->Arrays.sort(createRandomArray())); // () - метод не принимает параметров
        ExecutiveFunction.measure(()-> Arrays.sort(createRandomArray())); // аналог метода выше

        String s = "Hello ";
        Double d = 0.123;

        TransformUtils<Double> doubleUtils = new TransformUtils<>();
        //doubleUtils.transform(d, x -> Math.sin(x)); //лямбда
        System.out.println(doubleUtils.transform(d, Math::sin)); //метод reference (аналог предыдущей строки),
                                                                // так мызывается статический метод
                                                                //т.е. метод sin - static
        TransformUtils<String> strU = new TransformUtils<>();
        System.out.println(strU.transform(s,TransformUtils::exclaim)); //собственный статический метод

        String suffix = "Kate";
        //strU.transform(s, x -> x.concat(suffix)); //конкатенация двех строк
        System.out.println(strU.transform(suffix, s::concat));//тот же метод, только reference,
                                                            // когда используется инстанс класса и нестатический метод

        System.out.println(strU.transform(s, String::toUpperCase)); //метод reference
        System.out.println(strU.transform(s, String::new)); //метод reference


        LamdaScopeTest scopeTest = new LamdaScopeTest();
        LamdaScopeTest.LamdaScopeInner inner = scopeTest.new LamdaScopeInner();
        inner.testScope(9999.000);


        Circle circle = new Circle();
        System.out.println(circle.calcSomething());

        ExecutiveFunction operation1 = ()-> Arrays.sort(createRandomArray());
        ExecutiveFunction operation2 = ()-> Arrays.sort(createRandomArray());
        ExecutiveFunction.measure(operation1.combinerExecutiveFunction(operation2)); //сортировка двух массивов, то в одной операции
                                                // это преимузество дефолтного метода в интерфейсе



    }

    /*private static void processElements(List<Integer> intList, int a, ElementProcessor function)
    {
        List<Double> doubleList = new ArrayList<>();
        for (Integer i : intList)
        {
            doubleList.add(function.Process(i, a));
        }
        System.out.println(doubleList);
    }*/


    private static <T extends Number> void processElements(List<T> intList, ElementProcessor function)
    {
        List<Double> doubleList = new ArrayList<>();
        for (Number i : intList)
        {
            doubleList.add(function.Process(i));
        }
        System.out.println(doubleList);
    }

    private static double multiply(int x, int y)
    {
        return x * y / 10.0;
    }

    private static int[] createRandomArray()
    {
        int[] myArray = new int[1000000];
        Random r = new Random();
        for(int i = 0; i < myArray.length; i++)
        {
            myArray[i] = r.nextInt(myArray.length);
        }
        return myArray;
    }

    public static class TimeUtil {
//        private static void measure(ExecutiveFunction func){
//            long start = System.currentTimeMillis();
//            func.Process();
//            long end = System.currentTimeMillis();
//            System.out.println("Time spent " + (end-start));
//        }
    }
}
