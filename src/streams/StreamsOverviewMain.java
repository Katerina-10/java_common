package streams;


import lambdas.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsOverviewMain {
    private static List<Employee> emplist = new ArrayList<>();
    private static List<Employee> secondlist = new ArrayList<>();
    private static Map<Integer,Employee> empMap = null;

    public static void main(String[] args) throws IOException {
        emplist.add(new Employee(1,"Alex", "Black", 50000, "IT"));
        emplist.add(new Employee(2,"John", "Green", 75000, "IT"));
        emplist.add(new Employee(6,"Sam", "Brown", 80000, "IT"));
        emplist.add(new Employee(9,"Tony", "Grey", 90000, "IT"));
        emplist.add(new Employee(10,"Mike", "Yellow", 60000, "IT"));
        emplist.add(new Employee(11,"Vici", "Pink", 75000, "IT"));
        emplist.add(new Employee(16,"Saen", "Magenta", 80000, "Finance"));
        emplist.add(new Employee(19,"Kate", "Black", 88000, "Finance"));
        emplist.add(new Employee(9,"Tony", "Grey", 90000, "Finance"));
        emplist.add(new Employee(10,"Mike", "Yellow", 60000, "IT"));
        emplist.add(new Employee(11,"Vici", "Pink", 75000, "Finance"));

        secondlist.add(new Employee(1,"Alex", "Black", 50000));
        secondlist.add(new Employee(2,"John", "Green", 75000));
        secondlist.add(new Employee(6,"Sam", "Brown", 80000));
        secondlist.add(new Employee(9,"Tony", "Grey", 90000));
        secondlist.add(new Employee(10,"Mike", "Yellow", 60000));
        secondlist.add(new Employee(11,"Vici", "Pink", 75000));
        secondlist.add(new Employee(16,"Saen", "Magenta", 80000));
        secondlist.add(new Employee(19,"Kate", "Black", 88000));
        secondlist.add(new Employee(9,"Tony", "Grey", 90000));
        secondlist.add(new Employee(10,"Mike", "Yellow", 60000));
        secondlist.add(new Employee(11,"Vici", "Pink", 75000));

        //testStreamFromList();
        //testStreamFromFile();
        //testSortAndReduce();

        //partitionByIncome();

        //groupByCriterion(Employee::getDepartment);

        //testStreamGenerator(10);
        //testStreamIterator(10);

        //testParallelStream();

        //Supplier для чисел фибоначи
        Supplier<Integer> supplier = new Supplier<Integer>() {
            private int previous = 0;
            private int current = 1;
            @Override
            public Integer get() {
                int next = previous + current;
                previous = current;
                current = next;
                return current;
            }
        };

        testStreamGeneratorSup(10, supplier);


    }

    private static void testParallelStream() throws IOException
    {
        emplist
                .parallelStream()
                .map(Employee::getId)
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Files.lines(Paths.get("worlds.txt"))
                .parallel()
                .sorted();


    }

    public static <T> void testStreamGeneratorSup(int limit, Supplier<T> supplier)
    {
        Stream.generate(supplier)
                //.parallel() // несколько потоков могут одновременно складывать значения, что приведет к неточному результату
                .limit(limit)
                .forEach(System.out::println);
    }

    public static void testStreamIterator(int limit)
    {
        Stream.iterate(1, e -> e* 3)
        .limit(limit)
        .forEach(System.out::println); // не органичен в своих размерах, если не указать .limit(limit)
    }

    public static void testStreamGenerator(int limit)
    {
        Stream.generate(Math::random) // не органичен в своих размерах, если не указать .limit(limit)
                .limit(limit)
                .forEach(System.out::println);
    }

    public static <R> void groupByCriterion(Function<Employee, R> func)
    {
        Map<R, List<Employee>> collectedEmp = emplist.stream().collect(Collectors.groupingBy(func));
        collectedEmp.keySet().stream().forEach(e-> System.out.println(e + "\n" + collectedEmp.get(e)));
    }

    private static void partitionByIncome()
    {
        // группировка
        Map<Boolean, List<Employee>> collectEmp = emplist.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 70000));
        System.out.println("Poor emp"); // зп < 70 000
        System.out.println(collectEmp.get(false));
        System.out.println("Rich emp"); // зп > 70 000
        System.out.println(collectEmp.get(true));
    }

    private static void testSortAndReduce()
    {
        //методы сводящие все значения к одному
        Employee employee = emplist.stream()
                .max((e1, e2) -> e1.getId() - e2.getId()).get();

        Employee employee1 = emplist.stream()
                //.min((e1, e2) -> e1.getSalary() - e2.getSalary()).get();
                .min(Comparator.comparingInt(Employee::getSalary)).get(); //тоже только метод референс

        // не к одному, а сортировка
        emplist.stream() // отсортирован по имненам с помощью компоратора, поэтому он должен быть передан в качестве параметра
                .sorted((s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName())) //иначе  castException
                .distinct() // only unique
                .collect(Collectors.toList())
                .forEach(System.out::println);


        System.out.println(employee);
        System.out.println(employee1);

        Employee identity = new Employee(0, "","", 0);
        Employee reduceEmp = emplist.stream() //пример - сведенеине нескольких объектов в один
                .reduce(identity, (e1, e2) -> {
                    e1.setId(e1.getId() + e2.getId());
                    e1.setSalary(e1.getSalary() + e2.getSalary());
                    return e1;
                });
        System.out.println(reduceEmp);


    }

    private static void testStreamFromList()
    {
//        emplist.stream()
//                .filter(e->e.getSalary() > 60000).
//                .filter(e->e.getId() < 10)
//                .collect(Collectors.toList())
//                .forEach(System.out::println);

        Integer[] ids = {1,2,3,4,5,6,7,8,9,12,13,14,15,16,17,18,19,20};

        Stream.of(ids)
                .map(StreamsOverviewMain::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(System.out::println);

//        Optional<Employee> first = Stream.of(ids)
//                .map(StreamsOverviewMain::findById)
//                .filter(Objects::nonNull)
//                .findFirst();
//        System.out.println(first);

//        Stream.of(ids)
//                .filter(i-> i % 2 == 0)
//                .filter(i-> i % 3 == 0)
//                .filter(i-> i % 5 == 0)
//                .findFirst()
//                .get(); //ошибка, тк в массиве ids нет подходящего числа , альтернатива ниже
        Integer integer = Stream.of(ids)
                .filter(i-> i % 2 == 0)
                .filter(i-> i % 3 == 0)
                .filter(i-> i % 5 == 0)
                .findFirst()
                .orElse(0); //ошибки не будет, есть еще альтернатива
        System.out.println("integer = " + integer);

        Random r = new Random();
        Integer integer2 = Stream.of(ids)
                .filter(i-> i % 2 == 0)
                .filter(i-> i % 3 == 0)
                .filter(i-> i % 5 == 0)
                .findFirst()
                .orElseGet(()->r.nextInt());
        System.out.println("integer2 = " + integer2);

        Integer integer3 = Stream.of(ids)
                .filter(i-> i % 2 == 0)
                .filter(i-> i % 3 == 0)
                .limit(1)
                .findFirst()
                .orElseGet(()->r.nextInt());
        System.out.println("integer3 = " + integer3);

        Integer integer4 = Stream.of(ids)
                .filter(i-> i % 2 == 0)
                .filter(i-> i % 3 == 0)
                .skip(2) //skip(1) - пропустить первый элемент
                .findFirst()
                .orElseGet(()->r.nextInt());
        System.out.println("integer4 = " + integer4);



//        Stream.of(ids)
//                .map(StreamsOverviewMain::findById)
//                .filter(Objects::nonNull)
//                .mapToInt(Employee::getSalary)
//                .forEach(System.out::println)

        int sum = Stream.of(ids)
                .map(StreamsOverviewMain::findById)
                .filter(Objects::nonNull)
                .mapToInt(Employee::getSalary)
                .sum();
        System.out.println(sum);

        OptionalDouble avg = Stream.of(ids)
                .map(StreamsOverviewMain::findById)
                .filter(Objects::nonNull)
                .mapToInt(Employee::getSalary)
                .average();
        System.out.println(avg);

        OptionalInt max = Stream.of(ids)
                .map(StreamsOverviewMain::findById)
                .filter(Objects::nonNull)
                .mapToInt(Employee::getSalary)
                .max();
        System.out.println(max);


        List<List<Employee>> departments = new ArrayList<>();
        departments.add(emplist);
        departments.add(secondlist);

        departments.stream().flatMap(l -> l.stream().map(e->e.getFirstName())).forEach(System.out::println);

        Stream.of(ids)
                .peek(e->e = e *2).forEach(System.out::println);
        System.out.println("-----");
        int sumId = 0;
        //Stream.of(ids).forEach(e -> sum = sum + e); //не работает, т.к.
//        Consumer<Integer> c = e -> e = e * 2;
//        Stream.of(ids).forEach(c);


    }

    private static void testStreamFromFile() throws IOException
    {
        Files.lines(Paths.get("worlds.txt"))
                .filter(e -> e.length() > 4)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("---- альтернатива---");

        Files.lines(Paths.get("worlds.txt"))
                .filter(e -> e.length() > 4)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);
    }

    private static Employee findById(int id)
    {
        if (empMap == null){
            empMap = emplist.stream().distinct().collect(Collectors.toMap(Employee::getId,e->e));
        }
        return empMap.get(id);
    }

}
