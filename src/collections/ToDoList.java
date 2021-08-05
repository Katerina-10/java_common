package collections;

import java.util.*;

public class ToDoList {
    //private List<String> toDoList = new ArrayList<>();
    private LinkedList<String> toDoList = new LinkedList<>();

    public void addToList(String task)
    {
        //toDoList.add(task);
        addInAlfabaticalOrder(task);
    }

    private boolean addInAlfabaticalOrder (String task)
    {
        ListIterator<String> listIt = toDoList.listIterator();
        while(listIt.hasNext())
        {
            int compar = listIt.next().compareTo(task);
            if (compar == 0)
            {
                System.out.println("task already exist in the list");
                return true;
            }
            else if(compar > 0)
            {
                listIt.previous();
                listIt.add(task);
                return true;
            }
        }
        toDoList.add(task);
        return true;
    }

    public void addToListAtPosition(String task, int pos)
    {
        toDoList.add(pos, task);
    }

    public void printList()
    {
        /*for(int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + " " + toDoList.get(i));
        }*/
        Iterator <String> iter = toDoList.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }


    }

    public void changeTask(int i , String task)
    {
        toDoList.set(i,task);
    }

    public void containsList(String task)
    {
        System.out.println(toDoList.contains(task));
    }

    public void removeTask(String task)
    {
        toDoList.remove(task);
    }

    public int getIndex(String task)
    {
        return toDoList.indexOf(task);
    }
}
