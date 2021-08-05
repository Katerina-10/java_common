package lambdas;

import java.util.function.Consumer;

public class LamdaScopeTest {
    double d = 0.123;

    class LamdaScopeInner{
        double d = 456.123;

        void testScope(double d)
        {
            Consumer<Double> res = e -> {
                System.out.println("d = " + d);
                System.out.println("e = " + e);
                System.out.println("this.d = " + this.d);
                System.out.println("LamdaScopeTest.this.d = " + LamdaScopeTest.this.d);
            };
            res.accept(789.00);
        }
    }
}
