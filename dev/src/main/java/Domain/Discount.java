package Domain;

import java.util.Date;

public class Discount {
    private int percentage;
    private Date startDate;
    private Date endDate;

    public Discount(int percentage, Date startDate, Date endDate) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPercentage() {
        return percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isDiscountValid() {
        Date currentDate = new Date();
        return currentDate.after(startDate) && currentDate.before(endDate);
    }
}
