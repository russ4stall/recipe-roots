package com.github.russ4stall.reciperoots.utilities;

/**
 * Date: 7/9/13
 * Time: 8:57 AM
 *
 * @author Russ Forstall
 */
public class FormatterTest {
    public static void main(String[] args) {



        System.out.println(Formatter.toHourMixedNumber(30, 60));
        System.out.println(Formatter.toHourMixedNumber(45, 60));
        System.out.println(Formatter.toHourMixedNumber(120, 15));
        System.out.println(Formatter.toHourMixedNumber(45, 0));
        System.out.println(Formatter.toHourMixedNumber(100, 20));
        System.out.println(Formatter.toHourMixedNumber(30, 30));
        System.out.println(Formatter.toHourMixedNumber(60, 0));
        System.out.println(Formatter.toHourMixedNumber(46, 57));

    }
}
