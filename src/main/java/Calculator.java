package main.java;

import java.text.NumberFormat;
import java.util.List;

public class Calculator {

    private final static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    Calculator() {}

    public static String getTotalAsString(int quantity, Price price){
        return  formatter.format(quantity*price.doubleValue());
    }

    public String toString(Price p){
        return formatter.format(p.doubleValue());
    }
    public static String toString(Double d){
        return formatter.format(d);
    }

    public static double multiply(Double dollar, Integer amount){
        return Double.parseDouble(dollar.toString()) * amount;
    }
    public static double multiply(Price dollar, Integer amount){
        return dollar.doubleValue() * amount;
    }

    public static Double getTotalAsDouble(double quantity, Price price){
        return quantity*price.doubleValue();
    }
}
