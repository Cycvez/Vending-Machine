/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.ui;

import com.mycompany.vandingmachine.dto.Product;
import java.math.BigDecimal;

import java.util.List;

/**
 *
 * @author carlo
 */
public class VendingMachineDisplay {

    public VendingMachineDisplay(UserIO io) {
        this.io = io;
    }

    private UserIO io;

    public void printMenu() {

        io.print("Selection Options");
        io.print("1. Coke - $2.50");
        io.print("2. Doritos - $1.50");
        io.print("3. Xbox - $250.00");
        io.print("4. Ps4 - $250.00");
        io.print("5. PC - $1250.00");
        io.print("6. Skittles - $1.00");
        io.print("7. Return Money");

    }

    public int getSelection() {
        return io.readInt("Please Make a Selection", 1, 7, 100);
    }

    public void displayInventory(List<Product> inventory) {
        for (Product currentProduct : inventory) {
            String ProductInfo = String.format("%s : #%s %s ",
                    currentProduct.getName(),
                    currentProduct.getItemCost(),
                    currentProduct.getStockLeft());
            io.print(ProductInfo);
        }
        io.readString("Please hit enter to continue.");

    }

    public String displayGetInitialBalanceBanner() {
        String initialBalance = io.readString("$");
        return initialBalance;
    }

    public String takeYourItem(String itemName) {
        String takeYourItem = io.readString("Please Take Your " + itemName
                + ": press ENTER to Continue;");
        return takeYourItem;
    }
    

    public void changeInCoins(String exactChange) {
        io.print(exactChange);
    }

    public boolean purchaseAnotherProduct(BigDecimal exactChange) {
        boolean keepRunning = true;
        
        io.readString("Your Current Balance is $"+exactChange.toString());

        io.print("Would You like to Purchase another Product");
        int response = io.readInt("1: Yes \n2: No", 1, 2, 888);

        if (response == 2) {
            keepRunning = false;
            io.print("Thanks For shopping at LOS Vending");
        }

        return keepRunning;
    }

    // ALL THE BANNERS
    public void dispalyThanksForUsingLosVending() {
        io.print("Thanks for Buying From LOS Vending!");
    }

    public void displayUknownCommandBanner() {
        io.print("***NOT VALID COMMAND***");
    }

    public void displayRefundMoneyBanner() {
        io.print("Refunding Money");
    }

    public void displayAdminOptionsMenu() {
        io.print("ADMIN OPTIONS");
    }

    public void displayInsertFundsBanner() {
        io.print("Please Insert Funds");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR===");
        io.print(errorMsg);
    }

}
