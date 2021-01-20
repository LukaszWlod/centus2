package com.centus.Image;

import java.util.Date;
import java.util.EventObject;

public class ExpenseEvent extends EventObject {
    private String amount;
    private String category;
    private String product;
    private Date selectedDate;




    public ExpenseEvent(Object source,String amount,
                        String category,String product,Date selectedDate){
        super(source);
        this.amount = amount;
        this.category = category;
        this.product = product;
        this.selectedDate = selectedDate;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }
}
