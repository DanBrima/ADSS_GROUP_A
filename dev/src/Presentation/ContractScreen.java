package Presentation;

import Domain.Contract;
import Domain.ProductInContract;
import Domain.Supplier;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class ContractScreen extends Screen {
    private Contract contract;

    public ContractScreen(PrintStream out, Scanner in, Supplier supplier, int contract) {
        super(out, in);
        this.contract = supplier.contracts().get(contract);
    }

    public ContractScreen(PrintStream out, Scanner in, Contract contract) {
        super(out, in);
        this.contract = contract;
    }

    @Override
    public int handleMsg() {

        String LEFT_ALIGN_FORMAT = "| %-9s | %-6s | %-6s | %-8s | %-20s |%n";

        this.out.println();
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        this.out.format("|  Product  | Count  | Price  | Discount |               Catalog ID             |%n");
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        for (int prodIndex = 0; prodIndex < this.contract.products().size(); prodIndex++) {
            ProductInContract product = this.contract.products().get(prodIndex);
            this.out.format(LEFT_ALIGN_FORMAT, product.name(), product.amount(), product.price(), product.priceWithDiscount(), product.supplierCatalogID());
        }

        return Constants.USER_NO_INPUT;
    }
}
