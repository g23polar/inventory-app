package main.java;

import lombok.Data;


@Data
public class Price extends java.math.BigDecimal {

    public Price(double val) {
        super(val);
    }
    public String toString(){
        return Calculator.getPriceAsString(this);
    }
}
