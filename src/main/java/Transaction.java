package main.java;

import lombok.Getter;
import lombok.Setter;

public class Transaction {


    public enum TransactionType{SALE, PURCHASE};
    public enum Status{SUCCESSFUL, INCOMPLETE, UNDEFINED};

    @Getter
    private TransactionType transactionType;

    @Getter
    private Item item;

    @Getter
    private Integer quantity;

    @Getter
    @Setter
    private Status status;

    public Transaction(TransactionType tt, Item item, Integer quantity){
        this.transactionType = tt;
        this.item = item;
        this.quantity = quantity;
        this.status = Status.UNDEFINED;
    }
    public Transaction(TransactionType tt, Item item, String quantity){
        this.transactionType = tt;
        this.item = item;
        this.quantity = Integer.parseInt(quantity);
        this.status = Status.UNDEFINED;
    }

    public String toString(){
        return transactionType.toString() + " " + item.toString() + " " + quantity.toString() +  " " + status.toString();
    }
}
