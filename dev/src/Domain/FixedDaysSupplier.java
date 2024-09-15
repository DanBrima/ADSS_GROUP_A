package Domain;

import Presentation.IO;

import java.util.List;
import java.util.stream.Stream;

public class FixedDaysSupplier extends Supplier {
    private List<WeekDay> arrivalDays;

    public FixedDaysSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, List<Contact> contacts, List<WeekDay> arrivalDays) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts);
        this.arrivalDays = arrivalDays;
    }

    public static FixedDaysSupplier getFixedDaysSupplierFromIO(IO io) {
        Supplier supplier = Supplier.getSupplierFromIO(io);
        int arrivalDayCount = io.readInt("Enter the number of arrival days:");
        List<WeekDay> arrivalDays = Stream.generate(() -> WeekDay.getWeekDayFromIO(io)).limit(arrivalDayCount).toList();

        return new FixedDaysSupplier(supplier.needsPickup(), supplier.activeAccount(), supplier.bankAccount(), supplier.paymentOption(), supplier.contacts(), arrivalDays);
    }

    public List<WeekDay> getArrivalDays() {
        return arrivalDays;
    }

    public void setArrivalDays(List<WeekDay> arrivalDays) {
        this.arrivalDays = arrivalDays;
    }
}
