package main.java;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
public class Item implements Comparable<Item> {

    private @Getter final String sku;
    private @Getter double price = 0.0;

    public Item(String newsku){
        this.sku = newsku;
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
