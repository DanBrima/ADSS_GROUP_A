package Service;

import Domain.Category;
import Domain.Discount;
import Repositories.CategoryRepository;
import Repositories.DiscountRepository;

import java.util.Date;

public class DiscountService {
//    public static void addItemDiscount(DiscountsHistory discountsHistoryRef, String itemType, int percentage) {
//        try {
//            discountsHistoryRef.discountList.add(new ItemDiscount(percentage, new Date(), new Date(),
//                    new ProductInStore(itemType, new BigDecimal(-1), "", new BigDecimal(-1), -1, null)));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void addCategoryDiscount(String categoryName, int percentage) {
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();

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

//        session.close();
    }
}
