package lambdas.model;

public class Rectangle implements Shape{
    public Rectangle() {
        System.out.println("create Rectangle");
    }

    @Override
    public double calcSquare() {
        return 3;
    }
}
