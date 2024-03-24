import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    static ByteArrayOutputStream baos;
    static PrintStream ps;
    static String name;
    static Account acc;
    static Cashable acc_cash;
    static Class cls;

    @BeforeAll
    static void preparing() {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        System.setOut(ps);
        name = "Иванов А.А.";
        acc = new Account(name);
        cls = acc.getClass();
        acc_cash = (Cashable) acc.getProxy();
    }

    @Test
    void getName() {
        Assertions.assertEquals(name, acc.getName());
    }

    @Test
    void constructor() {
        long i = Arrays.stream(cls.getDeclaredConstructors()).
                filter(x -> ((Constructor<?>) x).getParameterCount() == 0).
                count();
        Assertions.assertEquals(1, i);
    }

    @Test
    void sayAbout() {
        acc.sayAbout();
        String res = baos.toString();
        baos.reset();
        acc_cash.sayAbout();
        String res1 = baos.toString();
        baos.reset();
        Assertions.assertEquals(res, res1);
    }

    @Test
    void methodsCashable(){
        acc.setAmounts(Currencies.RUB,111);
        acc_cash.setAmounts(Currencies.RUB,111);
        double res = acc.rub();
        for(Integer i=0; i<=2; i++) {
            double res1 = acc_cash.rub();
            Assertions.assertEquals(i.toString() + res, baos.toString() + res1);
            baos.reset();
        }
        acc.setAmounts(Currencies.USD,222);
        res = acc.usd();
        for(Integer i=0; i<=2; i++) {
            double res1 = acc_cash.usd();
            Assertions.assertEquals(i.toString() + res, baos.toString() + res1);
            baos.reset();
        }
            boolean  bres = acc.isMultyVal();
        for(Integer i=0; i<=2; i++) {
            boolean bres1 = acc_cash.isMultyVal();
            Assertions.assertEquals(i.toString() + bres, baos.toString() + bres1);
            baos.reset();
        }
    }
}