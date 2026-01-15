package main.java;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
public class Item implements Comparable<Item> {

    private @Getter final String sku;
    private @Getter final Price price;

    public Item(String sku1, Price price1) {
        this.sku = sku1;
        this.price = price1;
    }
    public Item(String sku1, Double price1) {
        this.sku = sku1;
        this.price = new Price(price1);
    }


    @Override
    public String toString() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item other)) return false;
        return Objects.equals(this.sku, other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public int compareTo(Item o) {
        return this.sku.compareTo(o.sku);
    }
}
