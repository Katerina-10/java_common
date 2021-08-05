package collections.Map;

import collections.Set.Car;

import java.util.*;

public class MapExample {
    public static void main(String[] args)
    {
        Map<String, Integer> numbers = new HashMap<>();
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);
        //System.out.println(numbers.put("One", 3)); //выведет, то что изначально было записано и перезапишет значение
        //System.out.println(numbers.get("Two"));

        Map<Car, Integer> cars = new HashMap<>();
        cars.put(new Car("toyota","corolla", 50), 50);
        cars.put(new Car("audi","a5", 50), 51);
        cars.put(new Car("toyota","auris", 50), 52);

        //System.out.println(cars.get(new Car("audi","a5", 50)));

        Map<String,Integer> wordMap = new HashMap<>();
        System.out.println("enter text");
        Scanner scanner= new Scanner(System.in);
        String str = scanner.nextLine();
        String[] tokens = str.split(" ");
        for(String token: tokens)
        {
            String word = token.toLowerCase();
            Integer count = wordMap.get(word);
            if (count == null)
            {
                wordMap.put(word,1);
            }
            else
            {
                wordMap.put(word,count+1);
            }
        }

        //printMap(wordMap);
        //NavigableSet<WordWrapper> wordWrapperSet = convertToSet(wordMap); // для private static NavigableSet<WordWrapper> convertToSet
        Set<WordWrapper> wordWrapperSet = convertToSet(wordMap);
        //printSet(wordWrapperSet);
        //wordWrapperSet.add(new WordWrapper("ttt", 5));


    }

    private static void printSet(NavigableSet<WordWrapper> wordWrapperSet) {
        for (WordWrapper wp: wordWrapperSet)
        {
            System.out.println(wp);
        }
    }

    //private static NavigableSet<WordWrapper> convertToSet(Map<String, Integer> wordMap) {
    private static Set<WordWrapper> convertToSet(Map<String, Integer> wordMap) {
        wordMap.remove("a");
        wordMap.remove("the", 4);
        wordMap.replace("the", 9); //замена value на 9, по ключу "the"
        wordMap.replace("the", 4, 9); //замена value на 9, по ключу "the", если предыдущее value = 4

        NavigableSet<WordWrapper> wordSet = new TreeSet<>();
        for(Map.Entry<String, Integer> e : wordMap.entrySet())
        {
            wordSet.add(new WordWrapper(e.getKey(), e.getValue()));
        }
        //return wordSet; //NavigableSet
        // return new TreeSet<>(wordSet); //или строкой ниже
        return Collections.unmodifiableSet(wordSet); //чтобы элементы не были изменены, для проверки в main будет отшибка при попытке изменения в строке wordWrapperSet.add(new WordWrapper("ttt", 5));
    }



    private static void printMap(Map<String, Integer> wordMap) {
        Map<String, Integer> wordTereeMap = new TreeMap<>(wordMap);
        //Set<String> keys = wordMap.keySet();
        Set<String> keys = wordTereeMap.keySet();
        for (String key: keys)
        {
            System.out.printf("%-10s%-10s\n", key, wordMap.get(key));
        }
    }
}
