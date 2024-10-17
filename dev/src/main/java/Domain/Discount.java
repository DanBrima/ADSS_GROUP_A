package Domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Discount {
    @Column
    private int percentage;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Id
    @GeneratedValue
    private Long id;

    public Discount() {
    }

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
