package lambdas;

public class TransformUtils<T> {
    T transform(T t, Transformable<T> function)
    {
        return function.Transformable(t);
    }

    static String exclaim(String str)
    {
        return str.toUpperCase()+"!!!!";
    }
}
