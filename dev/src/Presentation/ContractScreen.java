package Presentation;

import Domain.*;
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
        // TODO print contract according to given ID

        String LEFT_ALIGN_FORMAT = "| %-9s | %-6s | %-6s | %-8s | %-20s |%n";

        this.out.println();
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        this.out.format("|  Product  | Count  | Price  | Discount |               Catalog ID             |%n");
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        for (int prodIndex = 0; prodIndex < this.contract.products().size(); prodIndex++) {
            ProductInContract product = this.contract.products().get(prodIndex);
            this.out.format(LEFT_ALIGN_FORMAT, product.name(), product.count(), product.price(), product.priceWithDiscount(), product.supplierCatalogID());
        }
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        this.out.format("+                   This contract %-7s wholesale discount                    +%n",(contract.isDiscount()) ? "has" : "not has");
        this.out.format("+-----------+--------+--------+----------+--------------------------------------+%n");
        this.out.println();

        return Constants.USER_NO_INPUT;
    }
}
