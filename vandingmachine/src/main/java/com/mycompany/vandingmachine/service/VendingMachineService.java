/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.service;

import com.mycompany.vandingmachine.dao.VendingMachineDao;
import com.mycompany.vandingmachine.dao.VendingMachineDaoException;
import com.mycompany.vandingmachine.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author carlo
 */
public class VendingMachineService {

    private VendingMachineDao dao;

    public VendingMachineService(VendingMachineDao dao) {
        this.dao = dao;
    }

    public void removeProduct(String itemName, String initialAmount) throws VendingMachineDaoException,
            NoItemInventoryException, InsufficientFundsException {

        checkForProduct(itemName);
        checkForEnoughFunds(itemName, initialAmount);
        dao.removeProduct(itemName);
    }

    public void checkForProduct(String itemName) throws VendingMachineDaoException, NoItemInventoryException {
        if (dao.getProductByName(itemName).getStockLeft().equals("0")) {
            throw new NoItemInventoryException(itemName + " is all SoldOut");
        }
    }

    public void checkForEnoughFunds(String itemName, String initialAmount) throws VendingMachineDaoException, InsufficientFundsException {
        String itemCost = dao.getProductByName(itemName).getItemCost();
        BigDecimal productCost = new BigDecimal(itemCost);
        BigDecimal initialFunds = new BigDecimal(initialAmount);
        //Big Decimal form for initialFunds<ProductCost
        if (initialFunds.compareTo(productCost) == -1) {
            throw new InsufficientFundsException("Not enough Funds to Purchase this Item");
        }

    }

    public String itemCostInString(String itemName) throws VendingMachineDaoException, NoItemInventoryException {
        Product p = dao.getProductByName(itemName);
        String itemCostInString = p.getItemCost();
        return itemCostInString;
    }

    public BigDecimal getExactChangeInBigDecimal(String itemName, String initialAmount) throws VendingMachineDaoException, NoItemInventoryException, UnsupportedOperationException {
        Product currentProduct = dao.getProductByName(itemName);
        String itemCost = currentProduct.getItemCost();

        BigDecimal initialFunds = new BigDecimal(initialAmount);
        BigDecimal productCost = new BigDecimal(itemCost);

        BigDecimal change = initialFunds.subtract(productCost);
        return change;
    }

    public String getCoins(BigDecimal exactChange) throws VendingMachineDaoException, NoItemInventoryException, UnsupportedOperationException {
        String getChangeInCoins = getChangeInCoins(exactChange);

        return getChangeInCoins;
    }

    public String getChangeInCoins(BigDecimal exactChange) {

        String quartersString = "";
        String dimesString = "";
        String nicklesString = "";
        String penniesString = "";

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = exactChange.divide(QUARTER, 0, RoundingMode.DOWN);
        quartersString = quarters.toString();

        BigDecimal remaindingChange = exactChange.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
        dimesString = dimes.toString();

        BigDecimal remaindingChange2 = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChange2.divide(NICKLE, 0, RoundingMode.DOWN);
        nicklesString = nickles.toString();

        BigDecimal remaindingChange3 = remaindingChange2.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChange3.divide(PENNY, 0, RoundingMode.DOWN);
        penniesString = pennies.toString();

        String coins = "Your change is: \n" + quartersString + "-Quartars \n"
                + dimesString + "-Dimes \n" + nicklesString
                + "-Nickles \n" + penniesString + "-Pennies";

        return coins;
    }

    public String getRefundInCoins(String initialAmount) {
        BigDecimal initialFunds = new BigDecimal(initialAmount);

        String quartersString = "";
        String dimesString = "";
        String nicklesString = "";
        String penniesString = "";

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialFunds.divide(QUARTER, 0, RoundingMode.DOWN);
        quartersString = quarters.toString();

        BigDecimal remaindingChange = initialFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
        dimesString = dimes.toString();

        BigDecimal remaindingChange2 = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChange2.divide(NICKLE, 0, RoundingMode.DOWN);
        nicklesString = nickles.toString();

        BigDecimal remaindingChange3 = remaindingChange2.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChange3.divide(PENNY, 0, RoundingMode.DOWN);
        penniesString = pennies.toString();

        String coins = "Your change is: \n" + quartersString + "-Quartars \n"
                + dimesString + "-Dimes \n" + nicklesString
                + "-Nickles \n" + penniesString + "-Pennies";

        return coins;
    }
}
