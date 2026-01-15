import main.java.*;


ArrayList<Transfer> transferList = new ArrayList<Transfer>();
private Inventory inventory;
private String input;
private String[] words;
private Scanner scanner;

void setup() throws FileNotFoundException {
    scanner = new Scanner(new File("skus.txt"));
    while (scanner.hasNextLine()) {
        input = scanner.nextLine();
        words = input.split(" ");
        runner();
    }
    scanner.close();
}

void main() throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    inventory = handleCreateInventory();
    setup();
    scanner = new Scanner(System.in);
    while (true) {
        System.out.println("Options:\texit\ttransferin (in) \ttransferout (out)\tsnapshot (s) \treset \tgetrules \taddrule \tremoverule \tlistrules (lr) \t listtransfers (lt)\n");
        System.out.print("Enter a command: ");
        input = scanner.nextLine();
        words = input.split(" ");
        runner();
        System.out.println("----------------------");
    }
}


void runner(){
    try{
        switch (words[0].toLowerCase().trim()) {
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
                inventory = new Inventory();
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
    return new Inventory();
}

private void handleTransferIn(Inventory inventory, String[] words) {
    Item item = new Item(words[1], Double.parseDouble(words[3])) ;
    String amount = words[2];
    TransferIn t = new TransferIn( item, Integer.parseInt(amount));
    TransferIn.doTransfer(inventory, t);
    transferList.add(t);
}

private void handleTransferOut(Inventory inventory, String[] words) {

    // sku , amount
    String sku = words[1];
    String amount = words[2];

    TransferOut t = new TransferOut( new Item(sku, 0.0), Integer.parseInt(amount));
    TransferOut.doTransfer(inventory, t);
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


