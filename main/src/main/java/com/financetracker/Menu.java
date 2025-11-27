package com.financetracker;
import java.util.Scanner;

public class Menu {
    // Constructor that fires the menu loop upon program start.
    public Menu(){
        Scanner inputScan = new Scanner(System.in);

        while(true){
            __showMenu__();

            int choice = inputScan.nextInt();
            // TODO: Add functionality of choices + error / logic handling.
        }
    }


    /* Modularise menu showing to avoid lengthy code in the main
    system loop */
    public static void __showMenu__(){
        System.out.println("""
=-=-= Welcome to the student expense manager! =-=-=
Please use the following options to interact with
the program:
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

            1 * Add a new expense
            2 * View all expenses
            3 * Show total expense
            4 * Show critical expenses
            5 * Exit
            """);
    }
}
