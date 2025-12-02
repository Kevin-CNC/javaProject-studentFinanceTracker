package com.financetracker;

public class Expense {
    // Define the protected attributes
    protected String expTitle,expDescription,dateOfExpense;
    protected double expAmount;

    // Constructor for expense class
    public Expense(String expTitle, String expDescription, double expAmount, String dateOfExpense ){
        this.expTitle = expTitle;
        this.expDescription = expDescription;
        this.expAmount = expAmount;
        this.dateOfExpense = dateOfExpense;
    }

    // Getters and setters:
    public String getTitle(){
        return this.expTitle;
    }

    public void setTitle(String newValue){
        expTitle = newValue;
    }

    public String getDesc(){
        return expDescription;
    }

    public void setDesc(String newValue){
       expDescription = newValue;
    }

    public double getAmount(){
        return expAmount;
    }

    public void setAmount(double newValue){
        expAmount = newValue;
    }

    public String getDate(){
        return dateOfExpense;
    }

    public void setDate(String newValue){
        dateOfExpense = newValue;
    }

    /////////////////////////////////////////

    public String getExpenseInfo(){
        /* Formulates the info in a 'receipt' kind of format
        The format is:

        Date: xx-xx-xxxx - Title: titleHere
        very long description here
        
        Amount: £xxx.xx */

        String info = "Date: "+getDate()+" - Title: "+getTitle()+"\n"+
        getDesc()+"\n\n"+
        "Amount: £"+getAmount();

        return info;
    }

}
