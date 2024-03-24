import java.lang.reflect.*;
import java.util.Arrays;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws InterruptedException  {
        System.out.println("Begin");
        System.out.println("===================proxy============");
        Account a = new Account("Иванов А.А.");
        Cashable a_cashable = (Cashable) a.getProxy();
        a_cashable.setAmounts(Currencies.RUB,111);
        a.setAmounts(Currencies.RUB,111);
        a_cashable.sayAbout();
        a.sayAbout();
        System.out.println(" мультивалютный = " + a_cashable.isMultyVal());
        System.out.println(" rub = " + a_cashable.rub());
        System.out.println(" мультивалютный = " + a.isMultyVal());
        System.out.println(" rub = " + a.rub());
        System.out.println(" мультивалютный = " + a_cashable.isMultyVal());
        System.out.println(" мультивалютный = " + a_cashable.isMultyVal());
        System.out.println(" rub = " + a_cashable.rub());
        System.out.println(" usd = " + a_cashable.usd());
        System.out.println(" rub = " + a_cashable.rub());
        System.out.println(" usd = " + a_cashable.usd());
        a_cashable.setAmounts(Currencies.USD,222);
        System.out.println(" мультивалютный = " + a_cashable.isMultyVal());
        System.out.println(" rub = " + a_cashable.rub());
        System.out.println(" мультивалютный = " + a_cashable.isMultyVal());
        System.out.println(" rub = " + a_cashable.rub());
        System.out.println(" usd = " + a_cashable.usd());
        System.out.println(" usd = " + a_cashable.usd());
        double d = a_cashable.usd();

    }
}