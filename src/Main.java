import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Method;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws InterruptedException  {
        System.out.println("Begin");
        System.out.println("===================proxy============");
        Account acc1 = new Account("Иванов А.А.");
        Cashable a1 = (Cashable) acc1.getProxy();
        a1.setAmounts(Currencies.RUB,111);
        a1.sayAbout();
        System.out.println(" мультивалютный = " + a1.isMultyVal());
        System.out.println(" rub = " + a1.rub());
        System.out.println(" мультивалютный = " + a1.isMultyVal());
        System.out.println(" rub = " + a1.rub());
        System.out.println(" usd = " + a1.usd());
        a1.setAmounts(Currencies.USD,222);
        System.out.println(" мультивалютный = " + a1.isMultyVal());
        System.out.println(" rub = " + a1.rub());
        System.out.println(" мультивалютный = " + a1.isMultyVal());
        System.out.println(" rub = " + a1.rub());
        System.out.println(" usd = " + a1.usd());
        System.out.println(" usd = " + a1.usd());
        double d = a1.usd();
        System.out.println(d);
        System.out.println(d);
  //      boolean b = a1.isMultyVal();
//        Account acc1 = new Account("Сидоров А.А.");
//        Account acc2 = new Account("Петров В.В.");
//
//        acc1.setAmounts(Currencies.RUB, 111);
//        acc1.setAmounts(Currencies.USD, 20);
//        acc1.setAmounts(Currencies.CHY, 30);
//        acc2.setAmounts(Currencies.USD,100);
//        acc2.setAmounts(Currencies.EUR,200);
//        acc2.setAmounts(Currencies.CHY,300);
//        System.out.println(acc1.getAmounts().entrySet());
//        acc1.sayAbout();
//        acc2.sayAbout();
//
//
//        Class cls = acc1.getClass();

//      list the fields
//        for(Field f : cls.getFields())
//        {
//            System.out.println(f.getName());
//        }
//        System.out.println("===============");
//        for(Field f : cls.getDeclaredFields())
//        {
//            System.out.println(f.getName());
//        }

//        //count of spec. constructors
//        long c = Arrays.stream(cls.getDeclaredConstructors()).
//                filter(x->((Constructor)x).AccessFlag.contains(AccessFlag.PRIVATE)).
//                filter(x->((Constructor)x).getParameterCount()==0).
//                count();
//        System.out.println(c);
//
        //========== open closed field
    //    System.out.println("============ open closed field");
    //    for(Field f : cls.getDeclaredFields())
    //    {
    //        System.out.println(f.getName());
    //    }
    //        Field f = cls.getDeclaredField("name");
    //        f.setAccessible(true);
    //        f.set(acc1,"");
    //        acc1.sayAbout();


//        //==============
//
//        //==============method invoking
//        System.out.println("\nmethod invoking\n");
//        Method method = cls.getMethod("setAmounts", Currencies.class, Integer.class);
//        System.out.println(method.getAnnotatedReturnType());
//        method.invoke(acc1,Currencies.CHY,10);
//        acc1.sayAbout();

        //and annotations are accessible too
//        System.out.println("annotaitions==========");
//       Arrays.stream(cls.getMethods()).
//               forEach(x-> Arrays.stream(x.getDeclaredAnnotations()).
//                        forEach(y->{
//                            System.out.println(x.getName() + " " + x.getGenericReturnType() + " " + y.toString());
//                        }));
    }
}