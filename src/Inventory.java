import java.util.HashMap;
//import Constants;
public class Inventory {

    private HashMap<Item, Integer> inventory; // sku ->quantity
    private HashMap<Rule, String> ruleSet;

    public Inventory() {
        this.inventory = new HashMap<>();
        this.ruleSet = new HashMap<Rule, String>();
    }

    protected HashMap<Item, Integer> getInventory() {
        return inventory;
    }


    protected void getInventorySnapshot(){
        if(inventory.isEmpty()){
            System.out.println("INVENTORY IS EMPTY !!!");
            return;
        }

        System.out.println("-------------------");
        System.out.println("INVENTORY SNAPSHOT");
        System.out.println("-------------------");


        for(Item item: inventory.keySet()){
            System.out.println(item.getSKU() + ": " + inventory.get(item));
        }
    }


    // RULE FUNCTIONALITY
    protected void removeRule(Rule rule){
        System.out.println("Removing rule " + rule);
        this.ruleSet.remove(rule);
        System.out.println("NEW RULESET: "+ ruleSet);
    }

    protected void addRule(Rule rule, String permission){

        for(Rule r : Constants.SUPPORTED_RULES){
            if(r.equals(rule)){
                this.ruleSet.put(rule, permission);
                return;
            }
        }
        System.out.println("This rule "+rule+ " does not exist, yet");
    }

    protected HashMap<Rule, String> getRuleSet(){
        return this.ruleSet;
    }
}
