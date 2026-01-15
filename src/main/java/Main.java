import main.java.*;


ArrayList<Transfer> transferList = new ArrayList<Transfer>();
private Inventory inventory;
private String input;
private String[] words;
private Scanner scanner;
private HashMap<Integer, Transaction> transactionLog;

void setup() throws FileNotFoundException {
    scanner = new Scanner(new File("skus.txt"));
    while (scanner.hasNextLine()) {
        input = scanner.nextLine();
        words = input.split(" ");
        router();
    }
    scanner.close();
}

void main() throws FileNotFoundException {
    inventory = handleCreateInventory();
    setup();
    scanner = new Scanner(System.in);
    while (true) {

        System.out.printf(
                "Options:%n" +
                        "\t%-22s %-22s %-22s %-22s%n" +
                        "\t%-22s %-22s %-22s %-22s%n" +
                        "\t%-22s %-22s %-22s %-22s%n",
                "exit",
                "transferin (in)",
                "transferout (out)",
                "snapshot (s)",
                "reset",
                "getrules",
                "addrule",
                "removerule",
                "listrules (lr)",
                "listtransfers (lt)",
                "gettransactions (gt)",
                ""
        );
        System.out.print("Enter a command: ");
        input = scanner.nextLine();
        words = input.split(" ");
        router();
        System.out.println("----------------------");
    }
}


void router(){
    try{
        switch (words[0].toLowerCase().trim()) {
            case "gettransasctions":case"gt":
                handleTransactionLogRequest();
            case "getrules":
                getSupportedRules();
                break;
            case "exit":
                handleExit(scanner);
                break;
            case "in": case"transferin":
                handleTransferIn(inventory, words);
                break;
            case "out": case"transferout":
                handleTransferOut(inventory, words);
                break;
            case "snapshot" : case "s":
                handleSnapshot(inventory);
                break;
            case "addrule":
                handleNewRule(inventory, words);
                break;
            case "listrules": case "lr":
                handleGetRules();
                break;
            case "removerule": case"rr":
                handleRemoveRule(inventory, new Rule(words[1]));
                break;
            case "listtransfers": case "lt":
                viewTransfers();
                break;
            case "reset":
                transactionLog = new HashMap<Integer, Transaction>();
                if (words.length >= 2) {
                    inventory = new Inventory(Double.parseDouble(words[1]));
                }
                else  {
                    inventory = new Inventory(Constants.MILLION);
                }
            case "":
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    } catch (Exception e) {
        System.out.println("Invalid input, try again.");
    }
}

private void handleTransactionLogRequest() {
    if(transactionLog.isEmpty()){
        System.out.println("TRANSACTION LOG IS EMPTY !!!");
        return;
    }

    System.out.println("-------------------");
    System.out.println("  TRANSACTION LOG");
    System.out.println("-------------------");

    for(Map.Entry<Integer, Transaction> entry : transactionLog.entrySet()){
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }

}

private void handleGetRules() {
    if(inventory.getRuleSet().isEmpty()){
        System.out.println("No rules found");
        return;
    }
    for(Rule rule: inventory.getRuleSet().keySet()){
        System.out.println(rule.getRuleName() + " : " + inventory.getRuleSet().get(rule));
    }
}

private void viewTransfers() {
    for(Transfer t: transferList){
        System.out.println(t.toString());
    }
}


private void handleRemoveRule(Inventory inventory, Rule rule) {
    inventory.removeRule(rule);
}
private void handleNewRule(Inventory inventory, String[] words) {
    Rule r = new Rule(words[1]);
    inventory.addRule(r, words[2]);
}

private Inventory handleCreateInventory() {
    System.out.println("Creating a new inventory.");
    transactionLog = new HashMap<>();
    return new Inventory();
}

private void handleTransferIn(Inventory inventory, String[] words) {
    Item item = new Item(words[1], Double.parseDouble(words[3])) ;
    String amount = words[2];
    Transaction transaction = new Transaction(Transaction.TransactionType.PURCHASE, item, amount);
    if(!executeTransaction(transaction)) return;
    inventory.calculateCapital(transaction);
    TransferIn t= new TransferIn( item, Integer.parseInt(amount));
    TransferIn.doTransfer(inventory, t);
    transferList.add(t);
}

private boolean executeTransaction(Transaction transaction) {
    // validate
    if(transaction.getTransactionType().equals(Transaction.TransactionType.PURCHASE)){
        double capRequired = Calculator.multiply(transaction.getItem().getPrice(), transaction.getQuantity());
        if (capRequired > inventory.getCapital()) {
            System.out.println("Tough luck buddy, $ " + (capRequired - inventory.getCapital())+ " short.");
            transaction.setStatus(Transaction.Status.INCOMPLETE);
            this.transactionLog.put(transactionLog.size() + 1, transaction);
            return false;
        }
    }
    transaction.setStatus(Transaction.Status.SUCCESSFUL);
    this.transactionLog.put(transactionLog.size() + 1, transaction);
    return true;
}


// TODO: sale capital increment for partials
private void handleTransferOut(Inventory inventory, String[] words) {
    String amount = words[2];
    Item item = new Item(words[1], Double.parseDouble(words[3])) ;
    TransferOut t = new TransferOut(item, Integer.parseInt(amount));
    if(TransferOut.doTransfer(inventory, t)) {
        // check transfer completion - then do transaction
        Transaction transaction = new Transaction(Transaction.TransactionType.SALE, item, amount);
        transaction.setStatus(Transaction.Status.SUCCESSFUL);
        transactionLog.put(transactionLog.size() + 1, transaction);
        inventory.calculateCapital(transaction);
    }
    transferList.add(t);
}

private void handleSnapshot(Inventory inventory) {
    inventory.getInventorySnapshot();
}

private void handleExit(Scanner scanner) {
    scanner.close();
    System.exit(0);
}

private void getSupportedRules(){
    System.out.println("Available rules:");
    int i = 1;
    for(Rule r: Constants.SUPPORTED_RULES){
        System.out.println(i + " : " +r.getRuleName());
        i++;
    }
}


