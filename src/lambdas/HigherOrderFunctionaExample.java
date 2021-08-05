package lambdas;

import lambdas.model.RichPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class HigherOrderFunctionaExample {

    public static void main(String[] args) {

        List<RichPerson> people = new ArrayList<>();
        people.add(new RichPerson("Alex", "Black",50000, 25));
        people.add(new RichPerson("John", "Green", 75000, 35));
        people.add(new RichPerson("Sam", "Brown", 80000, 40));
        people.add(new RichPerson("Tony", "Grey", 90000, 50));

        testPredicate(people);
        testFunction(people);
        testConsumer(people);


    }

    private static void testPredicate(List<RichPerson> persons)
    {
        System.out.println("testPredicate ");
        Predicate<RichPerson> isRich = x -> x.getSalary() >= 75000;
        Predicate<RichPerson> isYoung = y -> y.getAge() <= 40;
        System.out.println("isRich.and(isYoung)");
        findAll(persons,isRich.and(isYoung)).forEach(System.out::println);

        System.out.println("isRich.or(isYoung)");
        findAll(persons,isRich.or(isYoung)).forEach(System.out::println);

        System.out.println("isYoung.negate()");
        findAll(persons,isYoung.negate()).forEach(System.out::println);
    }

    private static <T> List<T> findAll(List<T> elements, Predicate<T> predicate)
    {
        List<T> fileredList = new ArrayList<>();
        for(T el : elements)
        {
            if (predicate.test(el))
                fileredList.add(el);
        }
        return fileredList;
    }

    private static void testFunction(List<RichPerson> persons)
    {
        System.out.println("testFunction");
        Function<RichPerson, String> name = x -> x.getFirstName() + " " + x.getLastName();
        Function<String, String> sayHello = y -> "hello " + y;
        Function<RichPerson, String> composedFunc = sayHello.compose(name);
        //transform(persons, composedFunc).forEach(System.out::println);
        List<String> transformList = transform(persons, composedFunc);

        Function<String,String> exclain = s -> s + "!!!";
        Function<String,String> toUpper = String::toUpperCase;
        transform(transformList, compose(toUpper, exclain)).forEach(System.out::println); //композитная функция из двух

    }

    private static <T> Function<T, T> compose(Function<T,T>... functions)
    {
        Function <T, T> res = Function.identity(); //получение начальной функции, начального значений
        for(Function<T, T> f: functions)
        {
            res = res.andThen(f);
        }
        return res;
    }

    private static <T,R> List<R> transform(List<T> elements, Function<T, R> func)
    {
        List<R> list = new ArrayList<>();
        for (T el : elements)
        {
            list.add(func.apply(el));
        }
        return list;
    }

    private static void testConsumer(List<RichPerson> persons)
    {
        Consumer<RichPerson> rise = x -> x.setSalary(x.getSalary() * 11 / 10);
        processList(persons, rise.andThen(System.out::println));
    }

    private static <T> void processList(List<T> elements, Consumer<T> consumer)
    {
        for (T el : elements)
        {
            consumer.accept(el);
        }
    }

}
