import Domain.Categories.Category;
import Domain.Categories.SubCategory;
import Domain.Categories.SubSubCategory;
import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;
import External.CashierDesk;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        //Example category
        Category categoryFruits = new Category("Fruits");

        //Example sub category
        SubCategory subCategoryCitrus = new SubCategory(categoryFruits, "Citrus Fruits");
        SubCategory subCategoryTropical = new SubCategory(categoryFruits, "Tropical Fruits");
        SubCategory subCategoryBerries = new SubCategory(categoryFruits, "Berries");
        SubCategory subCategoryPome = new SubCategory(categoryFruits, "Pome Fruits");
        SubCategory subCategoryMelons = new SubCategory(categoryFruits, "Melons");

        //Example sub sub category
        SubSubCategory subSubCategoryOranges = new SubSubCategory("Oranges", subCategoryCitrus);
        SubSubCategory subSubCategoryBananas = new SubSubCategory("Bananas", subCategoryTropical);
        SubSubCategory subSubCategoryMangoes = new SubSubCategory("Mangoes", subCategoryTropical);
        SubSubCategory subSubCategoryGrapes = new SubSubCategory("Grapes", subCategoryBerries);
        SubSubCategory subSubCategoryApples = new SubSubCategory("Apples", subCategoryPome);
        SubSubCategory subSubCategoryWatermelons = new SubSubCategory("Watermelons", subCategoryMelons);

        // Example items
        Item appleItem = new Item("Apple", new BigDecimal(10), "Itzik fruits", new BigDecimal(5), 200, subSubCategoryApples);
        Item bananaItem = new Item("Banana", new BigDecimal(15), "Itzik fruits", new BigDecimal(3), 100, subSubCategoryBananas);
        Item orangeItem = new Item("Orange", new BigDecimal(20), "Tropicana", new BigDecimal(4), 150, subSubCategoryOranges);
        Item mangoItem = new Item("Mango", new BigDecimal(5), "Tropicana", new BigDecimal(10), 50, subSubCategoryMangoes);
        Item grapesItem = new Item("Grapes", new BigDecimal(25), "Vineyard Select", new BigDecimal(2), 80, subSubCategoryGrapes);
        Item watermelonItem = new Item("Watermelon", new BigDecimal(8), "MelonLand", new BigDecimal(7), 300, subSubCategoryWatermelons);

        Store store = new Store();

        // Create the first shelf with apple and banana with specific quantities
        Shelf shelf1 = new Shelf();
        store.addShelf(shelf1);
        shelf1.addItemStack(new ItemStack(appleItem, 25));  // 25 apples
        shelf1.addItemStack(new ItemStack(bananaItem, 45)); // 45 bananas

        // Create the second shelf with orange and mango with specific quantities
        Shelf shelf2 = new Shelf();
        store.addShelf(shelf2);
        shelf2.addItemStack(new ItemStack(orangeItem, 30)); // 30 oranges
        shelf2.addItemStack(new ItemStack(mangoItem, 20));  // 20 mangoes

        // Create the third shelf with grapes and watermelon with specific quantities
        Shelf shelf3 = new Shelf();
        store.addShelf(shelf3);
        shelf3.addItemStack(new ItemStack(grapesItem, 50));  // 50 grapes
        shelf3.addItemStack(new ItemStack(watermelonItem, 10)); // 10 watermelons

        Storage storage = new Storage();
        ItemStack defectiveItems = new ItemStack(mangoItem, 5);
        storage.addDefectiveItemStack(defectiveItems);

        storage.addItemStack(new ItemStack(grapesItem, 75));
        storage.addItemStack(new ItemStack(appleItem, 50));
        storage.addItemStack(new ItemStack(bananaItem, 30));
        storage.addItemStack(new ItemStack(orangeItem, 40));
        storage.addItemStack(new ItemStack(mangoItem, 25));
        storage.addItemStack(new ItemStack(watermelonItem, 10));


        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in), storage, store);
        cashierDesk.turnOn();
    }
}