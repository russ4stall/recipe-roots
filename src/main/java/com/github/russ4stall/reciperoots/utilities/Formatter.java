package com.github.russ4stall.reciperoots.utilities;

import org.apache.commons.lang3.math.Fraction;

import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Date: 7/8/13
 * Time: 9:31 AM
 *
 * @author Russ Forstall
 */
public class Formatter {

    public static String toMixedNumber(double d) {
        String mixedNumber = null;
        String sWhole = null;
        int whole = (int) d;
        double remainder = d % 1;
        Fraction frac = Fraction.getFraction(remainder);
        int numerator = frac.getNumerator();
        int denominator = frac.getDenominator();

        if (whole == 0) {
            sWhole = "";
        } else {
            sWhole = Integer.toString(whole);
        }
        if (remainder == 0) {
            mixedNumber = sWhole;
        } else {
            mixedNumber = sWhole + " <sup>" +numerator + "</sup>" + "&frasl;<sub>" + denominator + "</sub> ";
        }
        return mixedNumber;
    }


    public static String toHourMixedNumber(int prepTime, int cookTime){
        int totalMinutes = prepTime + cookTime;
        if(totalMinutes <=0){
            return "No Time!";
        }
        if(totalMinutes < 60){
            return  totalMinutes + " minutes";
        }
        String hourMixedNumber = null;
        int whole = totalMinutes / 60;
        int remainder = totalMinutes % 60;
        Fraction frac = Fraction.getReducedFraction(remainder, 60);


        if (remainder == 0) {
            String time = "hours";
            if(whole == 1){
                time = "hour";
            }
                hourMixedNumber = whole + " " + time;


        } else {
            if(frac.equals(Fraction.ONE_QUARTER) || frac.equals(Fraction.ONE_HALF) || frac.equals(Fraction.THREE_QUARTERS)){
                int numerator = frac.getNumerator();
                int denominator = frac.getDenominator();
                hourMixedNumber = whole + " <sup>" +numerator + "</sup>" + "&frasl;<sub>" + denominator + "</sub> hours";
            }else {
                String time = "hrs";
                if (whole == 1){
                    time = "hr";
                }
                hourMixedNumber = whole + " " + time + " " + remainder + " mins";
            }
        }
        return hourMixedNumber;






    }


}
