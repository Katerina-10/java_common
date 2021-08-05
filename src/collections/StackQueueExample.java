package collections;

import java.sql.SQLOutput;
import java.util.*;

public class StackQueueExample {
    public static void main(String[] args) {
        //passengerProcessing();

        //Queue<Card> cardDeck = new PriorityQueue<>();
        Queue<Card> cardDeck = new PriorityQueue<>(52, new CardComparator());

        for (Card.Face face : Card.Face.values())
        {
            for(Card.Suit suit : Card.Suit.values())
            {
                cardDeck.offer(new Card(suit,face));
            }
        }

        /*for(int i = 0 ; i < 10; i++)
        {
            System.out.println(cardDeck.poll()); // вывод с удалением
        }*/

        //System.out.println("Size cardDeck = " + cardDeck.size());

        //System.out.println(cardDeck);

        /*Iterator<Card> it = cardDeck.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }*/

        //System.out.println(cardDeck.peek()); // вывод без удаления
        //System.out.println(cardDeck.peek());

        System.out.println("\n");

        Deque<Card> cards = new ArrayDeque<>();
        for(int i = 0; i < 10 ; i++)
        {
            cards.offerLast(cardDeck.poll());
        }

        for(int i = 0; i < 10 ; i++)
        {
            System.out.println(cards.pollLast());
        }

        Card card = new Card(Card.Suit.Spades, Card.Face.Ten);
        cards.removeFirstOccurrence(card);
        cards.removeLastOccurrence(card);

    }

    private static void passengerProcessing()
    {
        Stack<Passenger> bus = new Stack<>();
        Passenger pers = new Passenger("Pavel", "Pavlov");
        bus.push(new Passenger("Ivan", "Ivanov"));
        bus.push(new Passenger("Sergey", "Sergeev"));
        bus.push(pers);
        bus.push(new Passenger("Petr", "Petrov"));

        System.out.println("Last passenger "+ bus.peek());

        System.out.println("position pers " + bus.search(pers));

        while(!bus.empty())
        {
            System.out.println(bus.pop());
        }



    }
    private static class Passenger
    {
        private static int number;
        private String name;
        private String surname;

        public Passenger(String name, String surname) {
            number++;
            this.name = name;
            this.surname = surname;
        }

        public static int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        @Override
        public String toString() {
            return name+ " "+surname;
        }
    }

}
