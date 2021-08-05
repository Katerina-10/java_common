public class StringsMain {
    public static void main(String[] args) {
        String str1 = "Мама ";
        String str2 = "мыла ";
        String str3 = "раму";
        String rez = str1 + str2 + str3;
        System.out.println(rez);


        String[] animals = {"haski","morj"};
        String[] food = {"korm","mayso"};
        String rez1 = animals[0] + " est " + food[0];
        String rez2 = animals[1] + " est " + food[1];
        System.out.println(rez1 + "\n" + rez2);


        String[] auto = {"volvo","bmw", "marda"};
        String rez3 = "В гараже стоят машины: ";
        for (String a : auto)
        {
            rez3 = rez3 + a + ", ";
        }
        rez3 = rez3.substring(0,(rez3.length() - 2)); // извлечение подстроки с 0 по length() - 2 из строки
        rez3 = rez3 + ".";
        System.out.println(rez3);


        String str = "Послединй символ в этой строке - w";
        int last = str.length() - 1;
        System.out.println("str.charAt(last) = " + str.charAt(last)); //вывод конкретного символа из строки


        String str4 = "1 000 000 000";
        char[] chArray = str4.toCharArray();
        for(int i = 0; i < chArray.length; i++)
        {
            if (chArray[i] == ' ') chArray[i] = '.';
        }
        System.out.println(chArray);


        String str5 = "978-3-16-148410-0";
        String[] str6 = str5.split("-"); //разбиение строк
        for(String  s : str6)
            System.out.print(s + " ");
        System.out.println("");


        String str7 = "www.mySite.com"; //содержится ли построка в строке?
        System.out.println("нашел " + str7.contains("mySite")+ "  не нашел " + str7.contains("mySite.ru"));

        System.out.println("заканчивается на com " + str7.endsWith("com") + "  заканчивается на ru " + str7.endsWith("ru"));
        System.out.println("начинается с ww " + str7.startsWith("ww") + "  начинается с my( с 4 символа) " + str7.startsWith("my",4));

        System.out.println("индекс первого вхождения m = " + str7.indexOf("m"));
        System.out.println("индекс последнего вхождения m = " + str7.lastIndexOf("m"));
        System.out.println("индекс первого вхождения m после 5 символа = " + str7.indexOf("m", 5));
        System.out.println("индекс первого вхождения Si = " + str7.lastIndexOf("Si"));


        String str8 = "     Я помню ЧУДНОЕ мгновеьне     ";
        str8 = str8.trim(); //удаление символов пробела в начале и в конце строки
        System.out.println(str8);
        System.out.println(str8.toUpperCase()); //заглавные
        System.out.println(str8.toLowerCase()); //прописные

        String str9 = "1 000 000 000";
        System.out.print( "do  " + str9);
        str9 = str9.replace(" ", "."); //замена символов в строке
        System.out.print( "   ->  " + str9 + "\n");


        String str10 = "Я помню ЧУДНОЕ мгновеьне";
        String str11 = "Я помню чудное мгновеьне";
        System.out.println("equals  " + str10.equals(str11)); //сравнение строк
        System.out.println("equals  " +  str10.toLowerCase().equals(str11)); //сравнение строк
        System.out.println("compareTo  " +  str10.compareTo(str11)); //сравнение строк:  0 - hfdys;
        System.out.println("compareTo  " +  "aaa".compareTo("bbb")); //                 <0 - первая строка перед второй
        System.out.println("compareTo  " +  "bbb".compareTo("aaa")); //                 >0 - вторая строка перед первой
        System.out.println("compareToIgnoreCase " + str10.compareToIgnoreCase(str11)); //без учета регистра



    }
}
