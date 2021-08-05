package collections.Map.TreeMap;

import java.util.*;

public class TreeMapExample {
    public static void main(String[] args)
    {
        NavigableMap<StudentGrade, Set<SubjectGrade>> grades = createGrades();
        printGr(grades, false);
        StudentGrade border = grades.ceilingKey(new StudentGrade("", 80));//вернет самое маленькое значение, извсе, что выше 80
        SortedMap<StudentGrade, Set<SubjectGrade>> scholarShipStudents = grades.tailMap(border);
        System.out.println("-----------");
        printGr(scholarShipStudents, false);
        System.out.println("претендент");
        StudentGrade contender = grades.lowerKey(border);
        System.out.println(contender);

        NavigableMap<StudentGrade, Set<SubjectGrade>> scholarShipStudents1 = (NavigableMap<StudentGrade, Set<SubjectGrade>>) grades.tailMap(border);
        System.out.println("-----------");
        printGr(scholarShipStudents1.descendingMap(), false); // в обратном порядке
        System.out.println("претендент");
        StudentGrade contender1 = grades.lowerKey(border);
        System.out.println(contender1);

        System.out.println("hight grade student" + scholarShipStudents1.lastEntry());
        System.out.println("hight grade student" + scholarShipStudents1.descendingMap().firstEntry());
        //System.out.println("floorKey grade student" + scholarShipStudents1.floorKey(border));



    }


    private static void printGr(Map<StudentGrade, Set<SubjectGrade>> grades, boolean printVal)
    {
        Set<StudentGrade> aveGr = grades.keySet();
        for(StudentGrade gr : aveGr)
        {
            System.out.println(gr);
            if(printVal) System.out.println(grades.get(gr));
        }
    }

    private static NavigableMap<StudentGrade, Set<SubjectGrade>> createGrades()
    {
        Set<SubjectGrade> alexGr = new HashSet<>();
        alexGr.add(new SubjectGrade("Math",89));
        alexGr.add(new SubjectGrade("Phys",65));
        alexGr.add(new SubjectGrade("Hist",95));
        alexGr.add(new SubjectGrade("Liter",90));
        alexGr.add(new SubjectGrade("Chem",75));

        Set<SubjectGrade> jameGr = new HashSet<>();
        jameGr.add(new SubjectGrade("Math",75));
        jameGr.add(new SubjectGrade("Phys",80));
        jameGr.add(new SubjectGrade("Hist",55));
        jameGr.add(new SubjectGrade("Liter",70));
        jameGr.add(new SubjectGrade("Chem",80));

        Set<SubjectGrade> antGr = new HashSet<>();
        antGr.add(new SubjectGrade("Math",93));
        antGr.add(new SubjectGrade("Phys",91));
        antGr.add(new SubjectGrade("Hist",82));
        antGr.add(new SubjectGrade("Liter",78));
        antGr.add(new SubjectGrade("Chem",88));

        Set<SubjectGrade> vicGr = new HashSet<>();
        vicGr.add(new SubjectGrade("Math",73));
        vicGr.add(new SubjectGrade("Phys",65));
        vicGr.add(new SubjectGrade("Hist",75));
        vicGr.add(new SubjectGrade("Liter",66));
        vicGr.add(new SubjectGrade("Chem",50));

        Set<SubjectGrade> alinaGr = new HashSet<>();
        alinaGr.add(new SubjectGrade("Math",90));
        alinaGr.add(new SubjectGrade("Phys",70));
        alinaGr.add(new SubjectGrade("Hist",78));
        alinaGr.add(new SubjectGrade("Liter",88));
        alinaGr.add(new SubjectGrade("Chem",89));

        NavigableMap<StudentGrade, Set<SubjectGrade>> map = new TreeMap<>();
        map.put(new StudentGrade("alex", calcAve(alexGr)), alexGr);
        map.put(new StudentGrade("jame", calcAve(jameGr)), jameGr);
        map.put(new StudentGrade("ant", calcAve(antGr)), antGr);
        map.put(new StudentGrade("vic", calcAve(vicGr)), vicGr);
        map.put(new StudentGrade("alina", calcAve(alinaGr)), alinaGr);


        return map;
    }

    private static float calcAve(Set<SubjectGrade> setSG)
    {
        float sum = 0f;
        for ( SubjectGrade sg : setSG)
        {
            sum+=sg.getGrade();
        }
        return  sum/setSG.size();
    }
}
