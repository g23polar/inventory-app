package main.java;

import java.util.HashMap;

public class Inventory {

    private HashMap<Item, Integer> inventory; // sku -> quantity
    private HashMap<Rule, String> ruleSet;

    public Inventory() {
        this.inventory = new HashMap<>();
        this.ruleSet = new HashMap<Rule, String>();
    }

    protected HashMap<Item, Integer> getInventory() {
        return inventory;
    }


    public void getInventorySnapshot(){
        if(inventory.isEmpty()){
            System.out.println("INVENTORY IS EMPTY !!!");
            return;
        }

        System.out.println("-------------------");
        System.out.println("INVENTORY SNAPSHOT");
        System.out.println("-------------------");


        for(Item item: inventory.keySet()){
            System.out.println(item.getSku() + ": " + inventory.get(item));
        }
    }


    // RULE FUNCTIONALITY
    public void removeRule(Rule rule){
        System.out.println("Removing rule " + rule);
        this.ruleSet.remove(rule);
        System.out.println("NEW RULESET: "+ ruleSet);
    }

    public void addRule(Rule rule, String permission){

        for(Rule r : Constants.SUPPORTED_RULES){
            if(r.equals(rule)){
                this.ruleSet.put(rule, permission);
                return;
            }
        }
        System.out.println("This rule "+rule+ " does not exist, yet");
    }

    public HashMap<Rule, String> getRuleSet(){
        return this.ruleSet;
    }
}
