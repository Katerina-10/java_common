import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListMain {

    static public void main(String args[])
    {
        List <String>list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");


        Iterator<String> iter = list.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }

        for(String l : list)
        {
            System.out.println(l);
        }

        Object element = null;
        List list2 = new ArrayList();
        list2.add( element);
        list2.add("regf");

        list2.add(1 ,"gf");


        System.out.println(list2.size());
        for( Object l : list2)
        {
            System.out.println((String)l);
        }

        List list3 = new ArrayList();
        list3.addAll(list2);
        for( Object l : list2)
        {
            System.out.println((String)l);
        }

        System.out.println("индекс gf " + list2.indexOf("gf")); //индекс

        System.out.println("содержит gf " + list3.contains("gf")); //содержит?
        list2.remove(null);

        Collections.sort(list2);
        System.out.println("sorted list2");
        for( Object l : list2)
        {
            System.out.println((String)l);
        }


    }
}
