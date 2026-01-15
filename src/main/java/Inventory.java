package main.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Inventory {

    private HashMap<Item, Integer> inventory; // item -> quantity
    private HashMap<Rule, String> ruleSet;
    private final Calculator calculator = new Calculator();
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


        double runningTotal = 0.0;

        for(Item item: inventory.keySet()){
            String t = Calculator.getTotalAsString(inventory.get(item), item.getPrice());
            runningTotal += Calculator.multiply(inventory.get(item), item.getPrice());
            String s = item.getSku() + ": " + inventory.get(item);
            s += " : $ "  + t;
            System.out.println(s);
        }
        System.out.println("-------------------");
        System.out.println("Total : "
                + inventory.values().stream().collect(Collectors.summingInt(Integer::intValue))
                + " : " +  Calculator.toString(runningTotal)
        );
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
