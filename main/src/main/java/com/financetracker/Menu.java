package com.financetracker;
import java.util.Scanner;

public class Menu {
    // Constructor that fires the menu loop upon program start.
    public Menu(){
        Scanner inputScan = new Scanner(System.in);
        boolean inMenu = true;

        while(inMenu){
            __showMenu__();

            int choice = inputScan.nextInt();
            switch( choice ){ 
                case 1: 
                    System.out.println("Adding expense yes");
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
    
        inputScan.close();
    }


    /* Modularise menu showing to avoid lengthy code in the main
    system loop */
    public static void __showMenu__(){
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
