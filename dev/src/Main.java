import Domain.Items.Item;
import Domain.Items.ItemInstance;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;
import External.CashierDesk;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        Item appleItem = new Item("Apple", new BigDecimal(10), "Itzik fruits", new BigDecimal(5), 200, null);
        Item bananaItem = new Item("Banana", new BigDecimal(15), "Itzik fruits", new BigDecimal(3), 100, null);
        Item orangeItem = new Item("Orange", new BigDecimal(20), "Tropicana", new BigDecimal(4), 150, null);
        Item mangoItem = new Item("Mango", new BigDecimal(5), "Tropicana", new BigDecimal(10), 50, null);
        Item grapesItem = new Item("Grapes", new BigDecimal(25), "Vineyard Select", new BigDecimal(2), 80, null);
        Item watermelonItem = new Item("Watermelon", new BigDecimal(8), "MelonLand", new BigDecimal(7), 300, null);

        Store store = new Store();

        // Create the first shelf with apple and banana with specific quantities
        Shelf shelf1 = new Shelf();
        shelf1.addItemStack(new ItemStack(new ItemInstance(appleItem), 25));  // 25 apples
        shelf1.addItemStack(new ItemStack(new ItemInstance(bananaItem), 45)); // 45 bananas
        store.addShelf(shelf1);

        // Create the second shelf with orange and mango with specific quantities
        Shelf shelf2 = new Shelf();
        shelf2.addItemStack(new ItemStack(new ItemInstance(orangeItem), 30)); // 30 oranges
        shelf2.addItemStack(new ItemStack(new ItemInstance(mangoItem), 20));  // 20 mangoes
        store.addShelf(shelf2);

        // Create the third shelf with grapes and watermelon with specific quantities
        Shelf shelf3 = new Shelf();
        shelf3.addItemStack(new ItemStack(new ItemInstance(grapesItem), 50));  // 50 grapes
        shelf3.addItemStack(new ItemStack(new ItemInstance(watermelonItem), 10)); // 10 watermelons
        store.addShelf(shelf3);
        Storage storage = new Storage();

        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in), storage, store);
        cashierDesk.turnOn();
    }
}