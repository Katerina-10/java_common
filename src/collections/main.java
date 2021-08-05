package collections;

import java.util.*;

public class main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        //firstPrim();
        comparatorPrim();


    }

    private static void comparatorPrim()
    {
        /*String[] colors = {"yellow", "red", "green", "blue"};
        LinkedList<String> strArr = new LinkedList<>(Arrays.asList(colors));
        strArr.add("black");
        colors = strArr.toArray(new String[strArr.size()]);*/
        List<String> colors = new ArrayList<>();
        colors.add("yellow");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("black");

        System.out.println("befor sort"+ colors.toString());
        Collections.sort(colors);
        System.out.println("aftre sort"+ colors.toString());

        List <Card> decOfCards = new ArrayList<>();
        for (Card.Face face : Card.Face.values())
        {
            for(Card.Suit suit : Card.Suit.values())
            {
                decOfCards.add(new Card(suit,face));
            }
        }

        /*System.out.println("original decOfCards");
        for (int i = 0; i < decOfCards.size();i++)
        {
            System.out.printf("%-20s %s", decOfCards.get(i), (i+1)%4 == 0 ? "\n" : " ");
        }*/


        Collections.shuffle(decOfCards); //перемешать коллекцию

        Card card = new Card(Card.Suit.Dimond, Card.Face.Queen);
        Collections.sort(decOfCards);
        int i = Collections.binarySearch(decOfCards, card);
        if (i >= 0 )
            System.out.println("card position "+ (i+1));
        else
            System.out.println("card was not found");

        List<Card> cardList = new ArrayList<>(decOfCards);
        Collections.fill(cardList,card);
        Collections.addAll(cardList,card,card,card,card,card);
        Collections.copy(cardList,decOfCards);
        printOutput(cardList);

        System.out.println(Collections.frequency(cardList,card));

        System.out.println("min= " + Collections.min(cardList));
        System.out.println("max= " + Collections.max(cardList));

        System.out.println("\n\nafter shuffle decOfCards");
        //printOutput(decOfCards);

        //Collections.sort(decOfCards);
        /*Collections.sort(decOfCards, Collections.reverseOrder());
        System.out.println("\n\nafter sort decOfCards");
        for (int i = 0; i < decOfCards.size();i++)
        {
            System.out.printf("%-20s %s", decOfCards.get(i), (i+1)%4 == 0 ? "\n" : " ");
        }*/

        Collections.sort(decOfCards, new CardComparator());
        System.out.println("\n\nafter sort decOfCards my CardComparator");
        //printOutput((List<Card>) decOfCards);
    }

    private static void printOutput(List<Card> decOfCards) {
        for (int i = 0; i < decOfCards.size(); i++) {
            System.out.printf("%-20s %s", decOfCards.get(i), (i + 1) % 4 == 0 ? "\n" : " ");
        }
    }

    private static void firstPrim()
    {
        ToDoList list = new ToDoList();
        printOut();
        int param = scanner.nextInt();
        while (param!=0)
        {
            switch (param)
            {
                case 1:
                    System.out.println("add");
                    scanner.nextLine();
                    String task1 = scanner.nextLine();
                    list.addToList(task1);
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 2:
                    System.out.println("print");
                    list.printList();
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 3:
                    System.out.println("update");
                    System.out.println("id");
                    scanner.nextLine();
                    int i = scanner.nextInt();
                    System.out.println("new task");
                    scanner.nextLine();
                    String task3 = scanner.nextLine();
                    list.changeTask(i, task3);
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 4:
                    System.out.println("remove");
                    System.out.println("task to remove");
                    scanner.nextLine();
                    String task4 = scanner.nextLine();
                    list.removeTask(task4);
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 5:
                    System.out.println("index of");
                    System.out.println("name task");
                    scanner.nextLine();
                    String task5 = scanner.nextLine();
                    System.out.println("id task = "+ list.getIndex(task5));
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 6:
                    System.out.println("add at position");
                    System.out.println("number to id");
                    scanner.nextLine();
                    int j = scanner.nextInt();
                    System.out.println("new task");
                    scanner.nextLine();
                    String task6 = scanner.nextLine();
                    list.addToListAtPosition(task6, j);
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 7:
                    System.out.println("containsList");
                    System.out.println("task");
                    scanner.nextLine();
                    String task7 = scanner.nextLine();
                    list.containsList(task7);
                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                case 8:

                    System.out.println("replay");
                    param = scanner.nextInt();
                    break;
                default:
                    param = 0;
            }
        }
    }

    private static void printOut()
    {
        System.out.println(
                "1 - add\n" +
                "2 - print\n" +
                "3 - update\n" +
                "4 - remove\n" +
                "5 - index\n" +
                "6 - update at position\n" +
                "7 - containsList\n" +
                "0 - exit\n" +
                "after enter"
        );
    }
}
