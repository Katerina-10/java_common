package collections.Set;

import java.util.*;

public class SetExample {
    public static void main(String[] args)
    {
        /*String[] cards = {"Mini","Mercedes-Benz","Audi","VW","Smart","Toyota","Porsche"};
        String[] otherCards = {"Audi","Ford","GMS","Toyota","Chevrolet"};

        Set<String> carSet = new HashSet<>(Arrays.asList(cards));
        Set<String> otherCarSet = new HashSet<>(Arrays.asList(otherCards));

        Set<String> uniqueCars  = new HashSet<>(carSet);
        uniqueCars.addAll(otherCarSet);
        print(uniqueCars);


        Set<String> uniqueCars2  = new LinkedHashSet<>(carSet);
        uniqueCars2.addAll(otherCarSet);
        print(uniqueCars2);


        Set<String> uniqueCars3  = new TreeSet<>(carSet);
        uniqueCars3.addAll(otherCarSet);
        print(uniqueCars3);

        */

        Set<Car> sixCars = new HashSet<>();
        sixCars.add(new Car("audi","a4", 60));
        sixCars.add(new Car("bmw","tt", 120));
        sixCars.add(new Car("bmw","440i", 200));
        sixCars.add(new Car("vw", "polo", 35));

        Set<Car> europeCars = new HashSet<>();
        europeCars.add(new Car("toyota", "auris",40));
        europeCars.add(new Car("vw", "polo", 35));
        europeCars.add(new Car("vw","golf", 45));
        europeCars.add(new Car("reno","megan", 50));
        europeCars.add(new Car("reno","clio", 30));

        Set<Car> uniqueCars = new HashSet<>(sixCars);
        uniqueCars.addAll(europeCars);
        printCar(uniqueCars);

        /*printCar(sixCars);
        System.out.println("----------");
        sixCars.removeAll(europeCars);
        printCar(sixCars);

        System.out.println("----------");
        europeCars.removeAll(sixCars);
        printCar(europeCars);*/

        /*sixCars.retainAll(europeCars);
        uniqueCars.removeAll(sixCars);
        //printCar(sixCars);
        printCar(uniqueCars);*/

        //Set<Car> uniqueCarsTree = new TreeSet<>(sixCars);
        NavigableSet<Car> uniqueCarsTree = new TreeSet<>(sixCars);
        uniqueCarsTree.addAll(europeCars);
        printCar(uniqueCarsTree);

        //SortedSet<Car> cars = uniqueCarsTree.headSet(new Car("toyota", "auris",40));
        //SortedSet<Car> cars = uniqueCarsTree.tailSet(new Car("toyota", "auris",40));
        //SortedSet<Car> cars = uniqueCarsTree.subSet(new Car("toyota", "auris",40),
        //                                            new Car("audi","a4", 60));
        SortedSet<Car> cars = uniqueCarsTree.subSet(new Car("toyota", "auris",40), true,
                                                    new Car("audi","a4", 60), true);
        printCar(cars);

        printCar(uniqueCarsTree);
        System.out.println("higher " + uniqueCarsTree.higher(new Car("toyota", "auris",40)));
        System.out.println("lower " + uniqueCarsTree.lower(new Car("toyota", "auris",40)));
        System.out.println("ceiling " + uniqueCarsTree.ceiling(new Car("toyota", "auris",43)));
        System.out.println("floor " + uniqueCarsTree.floor(new Car("toyota", "auris",39)));


    }



    private static void printCar(Set<Car> mSet)
    {
        System.out.printf("\n%-20s %-20s %-20s\n", "car brand", "model", "price");
        for (Car car: mSet)
            System.out.printf("%-20s %-20s %-20s \n", car.getCarBrand(), car.getModel(),car.getPricePerDay());
    }

    private static void print(Set<String> mSet)
    {
        System.out.println(mSet);
    }
}
