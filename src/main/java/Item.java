package main.java;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private final String SKU;

    public Item(String newsku) {
        this.SKU = newsku;
    }

    public String getSKU() {
        return SKU;
    }

    @Override
    public String toString() {
        return SKU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item other)) return false;
        return Objects.equals(this.SKU, other.SKU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SKU);
    }

    @Override
    public int compareTo(Item o) {
        return this.SKU.compareTo(o.SKU);
    }
}
