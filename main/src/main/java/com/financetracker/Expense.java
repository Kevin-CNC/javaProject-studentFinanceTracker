package com.financetracker;
import java.sql.Date;

public class Expense {
    // Define the protected attributes
    protected String expTitle,expDescription;
    protected double expAmount;
    protected Date dateOfExpense;

    // Constructor for expense class
    public Expense(String given_expTitle, String given_expDescription, double given_expAmount, Date given_dateOfExpense ){
        expTitle = given_expTitle;
        expDescription = given_expDescription;
        expAmount = given_expAmount;
        dateOfExpense = given_dateOfExpense;
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
