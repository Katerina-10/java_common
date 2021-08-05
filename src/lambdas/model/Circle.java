package lambdas.model;

public class Circle extends Elipsis implements Shape, AnotherShape{
    public Circle() {
        System.out.println("create Circle");
    }

    @Override
    public double calcSquare() {
        return 1;
    }


    public double calcSomething() {
        return Shape.super.calcSomething(); //явное указание, из какого интерфейса будет взят метод calcSomething()
    }
}
