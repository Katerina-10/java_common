package lambdas;

import lambdas.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class LambdaFunctionalExample {
    public static void main(String[] args) {
        List<Employee> emp = new ArrayList<>();
        emp.add(new Employee("Alex", "Black", 50000));
        emp.add(new Employee("John", "Green", 75000));
        emp.add(new Employee("Sam", "Brown", 80000));
        emp.add(new Employee("Tony", "Grey", 90000));

        List<Person> people = new ArrayList<>();
        people.add(new Person("Alex", "Black",25));
        people.add(new Person("John", "Green", 30));
        people.add(new Person("Sam", "Brown", 32));
        people.add(new Person("Tony", "Grey", 34));

        //Predicate
        System.out.println(findMatch(emp, e -> e.getSalary() > 80000));
        System.out.println(findMatch(people, p -> p.getAge() > 32));

        //Function
        System.out.println("Total salary " + calc(emp, Employee::getSalary));
        System.out.println("Total age " + calc(people, Person::getAge));

        //BiFunction, ее частный случай BinaryOperation
        BinaryOperator<Integer> combiner = (n1, n2) -> n1 + n2;
        Integer ziro = 0;
        System.out.println("Combine salary " +combine(emp, ziro, Employee::getSalary, combiner));

        BinaryOperator<Integer> combiner2 = (n1, n2) -> Math.max(n1,n2); // = Math::max;
        System.out.println("Combine2 salary " +combine(emp, ziro, Employee::getSalary, combiner2));

        //Consumer - применяется, когда для коллекции нужно примнить метод форич, т.е. что-то сделать со всеми элементами коллекции
        emp.forEach(e -> e.setSalary(e.getSalary()*11/10)); // поднять всем зп на 10%
        emp.forEach(System.out::println);

        //supplier - например, рандомный выбор из коллекции
        Supplier[] shapes = {Circle::new, Rectangle::new, Square::new};
        Random r = new Random();
        for (int i = 0; i < 4 ; i ++) {
            int index = r.nextInt(3);
            Supplier supplier = shapes[index];
            supplier.get();
        }






    }

    private static <T> T findMatch (List<T> elems, Predicate<T> predicateFunc)
    {
        for(T e: elems)
        {
            if (predicateFunc.test(e))
                return e;
        }
        return null;
    }


    private static <T> int calc (List<T> elems, Function<T, Integer> func)
    {
        int sum = 0;
        for(T e: elems)
        {
            sum = sum + func.apply(e);
        }
        return sum;
    }

    private static <T,R> R combine (List<T> elems, R ziroElement, Function<T, R> func, BinaryOperator<R> combiner)
    {
        for(T e: elems)
        {
            ziroElement = combiner.apply(ziroElement, func.apply(e));
        }
        return ziroElement;
    }

}

