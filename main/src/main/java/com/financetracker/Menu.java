package com.financetracker;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    /* Holders for expenses to be added via the menu
    Made it an array list of Expense objects, as DiscountedExpense is a subclass of Expense, so it will fit into the same array list */
    private ArrayList<Expense> studentExpenses = new ArrayList<Expense>();

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
                         __ChooseExpense__(inputScan);
                         __showMenu__();
                        break;
                    case 2: 
                        __viewAllExpenses__();
                        __showMenu__();
                        break;
                    case 3: 
                        __sumAllExpenses__();
                        __showMenu__();
                        break;
                    case 4: 
                        __printHighestExpense__();
                        __showMenu__();
                        break;
                    case 5: 
                        System.out.println("Thank you for using Student Expense Manager!\nCome back soon!");
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Unrecognised choice, try again.");
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

    private void __ChooseExpense__(Scanner inputScan){
        /* Handles choice of expense between discounted and standard, along with gathering
        the required inputs to initialise both classes */

        System.out.println("Choose what type of expense you'd like to add:\n1* Standard Expense\n2* Discounted Expense");
        while(true) {
            try{
                int expenseChoice = inputScan.nextInt();
                inputScan.nextLine(); // Clear the scanner
                
                if( expenseChoice > 2 || expenseChoice < 1 ){
                    System.out.println("Invalid choice, please choose between said expenses.");
                }else {
                    // Understand if the expense chosen is standard or not, and determine which fields to ask
                    Boolean isStandard = (1 == expenseChoice);
                    String name = isStandard?"Standard":"Discounted";
                    System.out.println("Adding a "+name+" expense:");


                    String givenTitle,givenDesc,givenDate;
                    double givenAmount, givenDiscount = 0;

                    while(true){
                        // Name validation loop
                        while( true ){
                            System.out.print("Enter the name of your expense: ");
                            givenTitle = inputScan.nextLine();

                            // Not an optional field, it must be filled in
                            if (!givenTitle.isEmpty() || !givenTitle.isBlank()){
                                break;
                            }
                        }
                        

                        System.out.print("Enter a description for your expense (Optional): ");
                        givenDesc = inputScan.nextLine();

                        System.out.print("Enter the amount of the expense: ");
                        try{
                            givenAmount = inputScan.nextDouble();
                            
                            if ( givenAmount < 0 ){
                                throw new InputMismatchException() ;
                            }
                        
                            // Automatically catch and handle if user enters either a non-digit or anything below 0.
                        } catch (InputMismatchException e){
                            System.out.print("Invalid amount expense, try again: ");
                            while(true){
                                inputScan.nextLine(); // Clear the scanner

                                try{
                                    givenAmount = inputScan.nextDouble();
                                    if ( givenAmount < 0 ){
                                        throw new InputMismatchException() ;
                                    }

                                    break;

                                }catch (InputMismatchException n){
                                    System.out.print("Invalid amount expense, try again: ");
                                }
                            }
                        }

                        // Not a standard expense, so it must also require the discount field
                        // else just ignore
                        if (!isStandard){

                            System.out.print("Enter the amount of the discount (0-100): ");
                            try{
                                givenDiscount = inputScan.nextDouble();
                                
                                if ( givenDiscount < 0 || givenDiscount > 100 ){
                                    throw new InputMismatchException() ;
                                }
                            
                                // Automatically catch and handle if user enters either a non-digit or anything below 0.
                            } catch (InputMismatchException e){
                                System.out.print("Invalid discount amount, try again: ");
                                while(true){
                                    inputScan.nextLine(); // Clear the scanner

                                    try{
                                        givenDiscount = inputScan.nextDouble();
                                        if ( givenDiscount < 0 || givenDiscount > 100 ){
                                            throw new InputMismatchException() ;
                                        }

                                        break;

                                    }catch (InputMismatchException n){
                                        System.out.print("Invalid discount amount, try again: ");
                                    }
                                }
                            }

                        }


                        inputScan.nextLine(); // Clear the scanner

                        System.out.print("Enter the date of your expense in the format day-month-year: ");
                        try{
                            givenDate = inputScan.next();
                            
                            String[] fields = givenDate.split("-");
                            if( fields.length == 1){
                                throw new InputMismatchException("Couldn't split the string; Bad format.");
                            }else if( Integer.parseInt(fields[0]) < 1 || Integer.parseInt(fields[1]) < 1 || Integer.parseInt(fields[2]) < 1 ){
                                throw new InputMismatchException("One of the values provided are not high enough.");
                            }else if( Integer.parseInt(fields[0]) > 31 || Integer.parseInt(fields[1]) > 12){
                                throw new InputMismatchException("Either day or month are too high.");
                            }

                            
                        }catch (InputMismatchException e){
                            System.out.print("Invalid date format. Try again: ");
                            while(true){
                                try{
                                    givenDate = inputScan.next();
                            
                                    String[] fields = givenDate.split("-");
                                    if( fields.length == 1){
                                        throw new InputMismatchException("Couldn't split the string; Bad format.");
                                    }else if( Integer.parseInt(fields[0]) < 1 || Integer.parseInt(fields[1]) < 1 || Integer.parseInt(fields[2]) < 1 ){
                                        throw new InputMismatchException("One of the values provided are not high enough.");
                                    }else if( Integer.parseInt(fields[0]) > 31 || Integer.parseInt(fields[1]) > 12){
                                        throw new InputMismatchException("Either day or month are too high.");
                                    }

                                    // if all checks pass, just break out!
                                    break;


                                }catch (InputMismatchException n){
                                    System.out.print("Invalid date format:" + n.getMessage() + ". Try again: ");
                                }
                            }
                        }

                        inputScan.nextLine(); // Clear the scanner


                        // Temporary expense object, won't get added unless the user confirms.
                        Expense tempExpense;

                        // creating the object again related to the user's previous type choice
                        if( isStandard ){
                            tempExpense = new Expense(givenTitle, givenDesc, givenAmount, givenDate);
                        }else{
                            tempExpense = new DiscountedExpense(givenTitle, givenDesc, givenAmount, givenDate, givenDiscount);
                        }

                        
                        System.out.println("=-=-=-=-=-=-=-=-=-=\n"+tempExpense.getExpenseInfo()+"\n=-=-=-=-=-=-=-=-=-=");

                        System.out.println("Please enter 1 to confirm the expense & exit, 2 To confirm the expense and make a new one of the same type, 3 to retry, 0 to exit.");
                        int confirmChoice;
                        
                        try{
                            confirmChoice = inputScan.nextInt();

                            if( confirmChoice < 0 || confirmChoice > 2 ){
                                throw new InputMismatchException();
                            }

                            // Actual choice logic
                            if ( confirmChoice == 0 ){
                                // Simply exit by breaking out of the loop
                                break;
                            } else if( confirmChoice == 1 ){
                                // Add and break out
                                __AddExpense__(tempExpense);
                                break;
                            } else if( confirmChoice == 2 ){
                                // Add and continue
                                __AddExpense__(tempExpense);

                                inputScan.nextLine(); // clear scanner
                                continue;
                            } else if( confirmChoice == 3 ){
                                // User wants to retry, so just skip current iteration  

                                inputScan.nextLine(); // clear scanner
                                continue;
                            }

                        } catch (InputMismatchException e){
                            inputScan.nextLine(); // Clear the scanner
                            System.out.print("Invalid choice, try again.");
                            continue;
                        }
                    }
                }
                break;
            }
            catch (InputMismatchException wrongInpType){
            System.out.println("Invalid choice, please choose between said expenses.");
            continue;
            }

        }

    }

    private void __AddExpense__(Expense expense){
        studentExpenses.add(expense);
    }

    private void __viewAllExpenses__(){
        System.out.println("\n\nListing all expenses:\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // for each loop, every item in the expenses list array will be printed
        for (Expense expense : studentExpenses){
            System.out.println("=-=-=-=-=-=-=-=-=-=\n"+expense.getExpenseInfo()+"\n=-=-=-=-=-=-=-=-=-=");
        }
    }

    private void __printHighestExpense__(){
        // Implement a simple On algorithm to look through every expense, compare its' amount to the next one and swap if higher


        if (studentExpenses.size() < 1) {
            System.out.println("=-=-=-=-=-=-=-=-=-=\nNo highest available: No expenses\n=-=-=-=-=-=-=-=-=-=");

        }else{
            Expense currentTarget = studentExpenses.get(0); // Start with the first item

            for (Expense expense : studentExpenses){
                if( expense == currentTarget ){
                    continue; // Same expense, skip the iteration
                }

                // Just assume that we only swap if it's higher than the last
                if (currentTarget.getAmount() < expense.getAmount()){
                    currentTarget = expense;
                }
            }

            System.out.println("=-=-=-=-=-=-=-=-=-=\nThe highest expense is:\n"+currentTarget.getExpenseInfo()+"\n=-=-=-=-=-=-=-=-=-=");
        }

    }

    private void __sumAllExpenses__(){
        // first check if there are any expenses to begin with
        if (studentExpenses.size() < 1) {
            System.out.println("=-=-=-=-=-=-=-=-=-=\nNo total available: No expenses\n=-=-=-=-=-=-=-=-=-=");

        }else{

            double sumAccumulator = 0;

            // Using a simple loop to add every expense object's "expAmount" to the sum accumulator var
            // note: discounted expenses are added with discount
            for (Expense expense : studentExpenses){
                sumAccumulator = sumAccumulator + expense.getAmount() ;
            }

            System.out.println("=-=-=-=-=-=-=-=-=-=\nYour current total is: £"+ sumAccumulator +"\n=-=-=-=-=-=-=-=-=-=");

        }
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
        "            3 * Show total expenses\n" +
        "            4 * Show highest expense\n" +
        "            5 * Exit\n");
    }
}
