package Service;

import Domain.Category;
import Domain.Discount;
import Domain.ProductInStore;
import Repositories.CategoryRepository;
import Repositories.DiscountRepository;
import Repositories.ProductInStoreRepository;

import java.util.Date;

public class DiscountService {
    public static void addItemDiscount(String productName, int percentage) {
        DiscountRepository discountRepository = new DiscountRepository();
        Discount newDiscount = new Discount(percentage, new Date(), new Date());
        discountRepository.add(newDiscount);

        ProductInStoreRepository productInStoreRepository = new ProductInStoreRepository();

        for (ProductInStore product : productInStoreRepository.getAll()) {
            if(product.getProduct().getName().equals(productName)) {
                productInStoreRepository.addDiscount(product, newDiscount);
            }
        }
    }
    public static void addCategoryDiscount(String categoryName, int percentage) {
        DiscountRepository discountRepository = new DiscountRepository();
        Discount newDiscount = new Discount(percentage, new Date(), new Date());
        discountRepository.add(newDiscount);

        CategoryRepository categoryRepository = new CategoryRepository();
        boolean categoryExist = false;

        for (Category category : categoryRepository.getAll()) {
            if(category.getName().equals(categoryName)) {
                categoryRepository.addDiscount(category, newDiscount);
                categoryExist = true;
            }
        }

        if (!categoryExist) {
            Category category = new Category(categoryName);
            categoryRepository.add(category);
            categoryRepository.addDiscount(category, newDiscount);
        }
    }
}
