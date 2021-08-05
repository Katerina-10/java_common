package lambdas.model;

public class Square implements Shape{
    public Square() {
        System.out.println("create Square");
    }

    public double calcSquare()
    {
        return 2;
    }
}
