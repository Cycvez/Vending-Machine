/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.controller;

import com.mycompany.vandingmachine.dao.VendingMachineDaoException;
import com.mycompany.vandingmachine.service.InsufficientFundsException;
import com.mycompany.vandingmachine.service.NoItemInventoryException;
import com.mycompany.vandingmachine.service.VendingMachineService;
import com.mycompany.vandingmachine.ui.VendingMachineDisplay;
import java.math.BigDecimal;

/**
 *
 * @author carlo
 */
public class VendingMachineController {

    private VendingMachineDisplay display;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineDisplay display, VendingMachineService service) {
        this.service = service;
        this.display = display;
    }

    public void run() {

        boolean keepRunning = true;

        display.printMenu();
        display.displayInsertFundsBanner();
        String initialBalance = display.displayGetInitialBalanceBanner();

        while (keepRunning) {
            String newBalance;
            String itemName;

            int productSelection = getProductSelection();
            try {

                switch (productSelection) {

                    case 1:
                        itemName = "Coke";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 2:
                        itemName = "Doritos";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 3:
                        itemName = "Xbox";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 4:
                        itemName = "PS4";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 5:
                        itemName = "PC";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }

                        break;
                    case 6:
                        itemName = "Skittles";
                        vendProduct(itemName, initialBalance);
                        if (!display.purchaseAnotherProduct(getExactChangeInBigDecimal(itemName, initialBalance))) {
                            display.changeInCoins(service.getChangeInCoins(getExactChangeInBigDecimal(itemName, initialBalance)));
                            keepRunning = false;
                        } else {
                            newBalance = getExactChangeInBigDecimal(itemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 7:
                        display.displayRefundMoneyBanner();
                        display.changeInCoins(service.getRefundInCoins(initialBalance));
                        keepRunning = false;
                        break;
                    case 9:
                        display.displayAdminOptionsMenu();
                        break;
                    default:
                        display.displayUknownCommandBanner();
                }

            } catch (VendingMachineDaoException | NoItemInventoryException | InsufficientFundsException ex) {
                display.displayErrorMessage(ex.getMessage());

            }
        }
    }

    private int getProductSelection() {
        return display.getSelection();
    }

    private String vendProduct(String itemName, String initialBalance) throws VendingMachineDaoException,
            NoItemInventoryException, InsufficientFundsException {
        service.removeProduct(itemName, initialBalance);
        display.takeYourItem(itemName);
        return service.itemCostInString(itemName);
    }

    private BigDecimal getExactChangeInBigDecimal(String initialBalance, String itemName) throws VendingMachineDaoException,
            NoItemInventoryException {
        BigDecimal exactChange = service.getExactChangeInBigDecimal(initialBalance, itemName);
        return exactChange;

    }

}
