package domain;

import presentation.IO;

public enum PaymentOption {
    CASH, CREDIT_CARD;

    public static PaymentOption getPaymentOptionFromIO(IO io) {
        return valueOf(io.readString("Enter the payment option (CASH/CREDIT_CARD):"));
    }
}
