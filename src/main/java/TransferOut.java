package main.java;
import java.util.HashMap;

public class TransferOut extends Transfer{
    private final int amount;
    private final Item item;

    public TransferOut(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    protected int getAmount() {
        return this.amount;
    }

    protected Item getItem() {
        return this.item;
    }
    public static void doTransfer(Inventory inventory, TransferOut transferOut) {

        HashMap<Item, Integer> current = inventory.getInventory();
        Item item = transferOut.getItem();
        int amountRequested = transferOut.getAmount();
        if(current.containsKey(item)) {
            int currentAmount= current.get(item);
            if(amountRequested > currentAmount) {
                boolean permittedPartials = false;
                for (Rule r: inventory.getRuleSet().keySet()) {
                    if(r.equals(Constants.allowpartial) && inventory.getRuleSet().get(r).equals("Y")) {
                        permittedPartials = true;
                    }
                }
                if(!permittedPartials){
                    System.out.println("Don't have enough, not permitted to do partials");
                } else {
                    current.remove(item);
                }
            }
            else {
            currentAmount -= amountRequested;
            current.put(item, currentAmount);
            System.out.println("Item " + item + " updated to " + currentAmount);
            }
        }
        else{
            System.out.println("Item " + item + " not found");
        }
    }

    public String toString(){
        return this.item + " " + this.amount  + " OUT";
    }

}
