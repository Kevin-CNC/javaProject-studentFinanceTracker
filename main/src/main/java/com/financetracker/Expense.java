package com.financetracker;
import java.sql.Date;

public class Expense {
    // Define the protected attributes
    protected String expTitle,expDescription;
    protected double expAmount;
    protected Date dateOfExpense;

    // Constructor for expense class
    public Expense(String expTitle, String expDescription, double expAmount, Date dateOfExpense ){
        this.expTitle = expTitle;
        this.expDescription = expDescription;
        this.expAmount = expAmount;
        this.dateOfExpense = dateOfExpense;
    }

    // Getters and setters:
    public String getTitle(){
        return expTitle;
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

    public Date getDate(){
        return dateOfExpense;
    }

    public void setAmount(Date newValue){
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
