package com.financetracker;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    /* Holders for expenses to be added via the menu
    Made it an array list of Expense objects, as DiscountedExpense is a subclass of Expense, so it will fit into the same array list */
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
                         __ChooseExpense__(inputScan);
                         __showMenu__();            
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
                }else if( expenseChoice == 2 ){
                    System.out.println("Adding: Discounted expense");
                    String givenTitle,givenDesc,givenDate;
                    double givenAmount = 0;

                    while(true){
                        System.out.print("Enter the name of your expense: ");
                        givenTitle = inputScan.nextLine();
                        

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

                                }catch (InputMismatchException n){
                                    System.out.print("Invalid date format:" + n.getMessage() + ". Try again: ");
                                }
                            }
                        }

                        inputScan.nextLine(); // Clear the scanner


                        // Temporary expense object, won't get added unless the user confirms.
                        Expense tempExpense = new Expense(givenTitle, givenDesc, givenAmount, givenDate);
                        System.out.println("=-=-=-=-=-=-=-=-=-=\n"+tempExpense.getExpenseInfo()+"\n=-=-=-=-=-=-=-=-=-=");

                        System.out.println("Please enter 1 to confirm the expense & exit, 2 To confirm the expense and make a new one, 3 to retry, 0 to exit.");
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
                                continue;
                            } else if( confirmChoice == 3 ){
                                // User wants to retry, so just skip current iteration  
                                continue;
                            }

                        } catch (InputMismatchException e){
                            inputScan.nextLine(); // Clear the scanner
                            System.out.print("Invalid choice, try again.");
                            continue;
                        }
                    }

                }else if( expenseChoice == 1 ){
                    System.out.println("Adding: Standard expense");
                    String givenTitle,givenDesc,givenDate;
                    double givenAmount = 0;

                    while(true){
                        System.out.print("Enter the name of your expense: ");
                        givenTitle = inputScan.nextLine();
                        

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

                                }catch (InputMismatchException n){
                                    System.out.print("Invalid date format:" + n.getMessage() + ". Try again: ");
                                }
                            }
                        }

                        inputScan.nextLine(); // Clear the scanner


                        // Temporary expense object, won't get added unless the user confirms.
                        Expense tempExpense = new Expense(givenTitle, givenDesc, givenAmount, givenDate);
                        System.out.println("=-=-=-=-=-=-=-=-=-=\n"+tempExpense.getExpenseInfo()+"\n=-=-=-=-=-=-=-=-=-=");

                        System.out.println("Please enter 1 to confirm the expense & exit, 2 To confirm the expense and make a new one, 3 to retry, 0 to exit.");
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
                                continue;
                            } else if( confirmChoice == 3 ){
                                // User wants to retry, so just skip current iteration  
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

    private void __AddExpense__(Object expense){
        studentExpenses.add(expense);
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
