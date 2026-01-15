package main.java;
import java.util.HashMap;

public class TransferIn extends Transfer {

    private int amount;
    private Item item;

    public TransferIn(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    protected int getAmount() {
        return this.amount;
    }

    protected Item getItem() {
        return this.item;
    }

    public static void doTransfer(Inventory inventory, TransferIn transferIn) {
        HashMap<Item, Integer> current = inventory.getInventory();
        Item item = transferIn.getItem();
        int amount = transferIn.getAmount();
        if(current.containsKey(transferIn.getItem())) {
            int currentAmount= current.get(item);
            currentAmount += amount;
            current.put(item, currentAmount);
            System.out.println("Item " + item + " updated to " + currentAmount);
        }
        else{
            current.put(item, amount);
        }
    }

    public String toString(){
        return this.item + " " + this.amount  + " IN ";
    }
}
