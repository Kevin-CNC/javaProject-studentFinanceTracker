package com.financetracker;
import com.financetracker.Expense;

import java.sql.Date;

public class DiscountedExpense extends Expense {
    protected double expenseDiscount;

    public DiscountedExpense(String expTitle, String expDescription, double expAmount, Date dateOfExpense, double expenseDiscount){
        super(expTitle, expDescription, expAmount, dateOfExpense);
        this.expenseDiscount = expenseDiscount;
    }

}
