package com.financetracker;

public class DiscountedExpense extends Expense {
    protected double expenseDiscount;

    // Since it's a subclass of expense, initialise the parent class' constructor
    public DiscountedExpense(String expTitle, String expDescription, double expAmount, String dateOfExpense, double expenseDiscount){
        super(expTitle, expDescription, expAmount, dateOfExpense);
        this.expenseDiscount = expenseDiscount;
    }

    public double getDiscount(){
        return expenseDiscount;
    }

    public void setDiscount(double newVal){
        expenseDiscount = newVal;
    }

    // specific method to get an expense's amount BEFORE discount
    public double getOGAmount(){
        return expAmount;
    }



    // Methods that need to be overriden to adapt to the discount
    @Override public double getAmount(){
        return expAmount * (1 - (expenseDiscount/100));
    }

    @Override public String getExpenseInfo(){
        /* Formulates the info in a 'receipt' kind of format
        The format is:

        Date: xx-xx-xxxx - Title: titleHere
        very long description here
        
        Amount: £xxx.xx - Discount: xx% 
        (Original Amount: £xxx.xx)      */

        String info = "Date: "+getDate()+" - Title: "+getTitle()+"\n"+
        getDesc()+"\n\n"+
        "Amount: £"+getAmount()+" - Discount: "+getDiscount()+"%\n"+
        "(Original Amount: £"+getOGAmount()+" )";

        return info;
    }

}
