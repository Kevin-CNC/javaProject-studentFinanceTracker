package com.financetracker;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    /* Holders for expenses to be added via the menu
    Directly using the object type as we will keep both Expense and DiscountExpense objects in the same array */
    private ArrayList<Object> studentExpenses = new ArrayList<Object>();

    // Constructor that fires the menu loop upon program start.
    public Menu(){
        Scanner inputScan = new Scanner(System.in);
        boolean inMenu = true;

        __showMenu__();
        while(inMenu){
            try{
                int choice = inputScan.nextInt();
                switch( choice ){ 
                    case 1:
                        Object resultExpense =

                        __AddExpense__();
                        break;
                    case 2: 
                        System.out.println("view expenses yes");
                        break;
                    case 3: 
                        System.out.println("show total expenses yes");
                        break;
                    case 4: 
                        System.out.println("show critical expense yes");
                        break;
                    default: 
                        System.out.println("exiting yes");
                        inMenu = false;
                        break;
                }
            }
            /* nextInt scan can throw this exception in case the user puts anything that isn't an int
            so we gracefully handle it to keep the program running */
            catch (InputMismatchException wrongInpType){
                System.out.println("Invalid choice! Please choose again.");
            }

        }
    
        inputScan.close();
    }


    // Expense addition functions; Handles both discounted and non-discounted expenses

    private static Object __ChooseExpense__(Scanner inputScan){
        /* Handles choice of expense between discounted and standard, along with gathering
        the required inputs to initialise both classes */

        System.out.println("Choose what type of expense you'd like to add:\n1* Standard Expense\n2* Discounted Expense");
        while(true) {
            inputScan.next(); // Clear the scanner

            try{
                int expenseChoice = inputScan.nextInt();
                
                if( expenseChoice > 2 || expenseChoice < 1 ){
                    System.out.println("Invalid choice, please choose between said expenses.");
                }else if( expenseChoice == 2 ){
                    



                }else if( expenseChoice == 1 ){

                }
            }
            catch (InputMismatchException wrongInpType){
            System.out.println("Invalid choice, please choose between said expenses.");
            }

        }

    }

    private static void __AddExpense__(){
    }




    /* Modularise menu showing to avoid lengthy code in the main
    system loop */
    private static void __showMenu__(){
        System.out.println("=-=-= Welcome to the student expense manager! =-=-=\n"+
        "Please use the following options to interact with\n"+
        "the program:\n"+
        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"+
        "            1 * Add a new expense\n"+
        "            2 * View all expenses\n" +
        "            3 * Show total expense\n" +
        "            4 * Show critical expenses\n" +
        "            5 * Exit\n");
    }
}
