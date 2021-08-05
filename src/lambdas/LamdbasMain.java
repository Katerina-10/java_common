package lambdas;

import java.util.Arrays;
import java.util.Comparator;

public class LamdbasMain {
    public static void main(String[] args)
    {
        String[] colors = {"green", "brow","black", "blue", "pink", "grey"};

        Player player1 = new Player("Alex", 100);
        Player player2 = new Player("Igor", 80);
        Player player3 = new Player("Vic", 60);
        Player player4 = new Player("Joe", 51);

        Player[] players = {player1, player2, player3, player4};


        Arrays.sort(colors);
        System.out.println(Arrays.toString(colors));
        //способ из 1.7 java
        Arrays.sort(players, new Comparator<Player>(){ //создание внутреннего анонимного класса
            @Override
            public int compare(Player p1, Player p2){ //сорт от большего к меньшему
                return p2.score - p1.score;
            }
        } );


        //лямбда выражение
        Arrays.sort(players, (Player p1, Player p2) -> { //ожно удалить тип, т.е (p1, p2), компилятор сам поймет, какой тип,
                                                              // т.к. в компораторе метода sort тип - Т
                if ((p2.score - p1.score)!=0) // если количество очков разное
                {
                    return p2.score - p1.score; //сортировка по оскам
                }
                else // если количество очков одинаковое, то вернуть результат сравнения имен
                {
                    return p1.name.compareTo(p2.name);
                }
            }
         );


        System.out.println(Arrays.toString(players));


        Arrays.sort(colors, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) { //сорт по последней букве в имени
                return s1.charAt(s1.length()-1) - s2.charAt(s2.length()-1);
            }
        });


        System.out.println(Arrays.toString(colors));


        //лямбда выражние
        Arrays.sort(colors,(String s1, String s2) -> {
            System.out.println(s1 + " " + s2);
            return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
        } );

        //укороченное лямбда выражние, если один return
        //Arrays.sort(colors,(String s1, String s2) -> s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1) );


        System.out.println(Arrays.toString(colors));



    }

    private static class Player {
        private String name;
        private int score;

        public Player(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }

    }
}
