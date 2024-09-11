package Domain.Discounts;

import java.util.Date;
public abstract class Discount {
    private int percentage;
    private final Date startDate;
    private Date endDate;

    public Discount(int percentage, Date startDate, Date finalDate) throws Exception {
        this.setPercentage(percentage);
        this.startDate = startDate;
        this.setFinalDate(finalDate);
    }

    public int getPercentage() {
        return percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinalDate() {
        return endDate;
    }

    public void setFinalDate(Date finalDate) throws Exception {
        if (this.startDate.before(finalDate) || this.startDate.equals(finalDate))
            this.endDate = finalDate;
        else
            throw new Exception("The end date cant be before the start date");
    }

    public void setPercentage(int percentage) throws Exception {
        if (percentage > 0 && percentage <= 100)
        this.percentage = percentage;
        else
            throw new Exception("The percentage given isn't between 0 and 100 (zero is invalid)");
    }
}
