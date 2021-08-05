package generic;

public class MyBox<X> {

    X someField; // T someField - будет ошибкой,
                 // можно использовать тип, которым параметризован класс, те X



    public <T> MyBox(T param /*, X param2*/)
    {
        T someName = param;
        //someField = param2;
        System.out.println(param.getClass().getSimpleName());
    }

    public static <U> U returnValue(U param)
    {
        return param;
    }

}
