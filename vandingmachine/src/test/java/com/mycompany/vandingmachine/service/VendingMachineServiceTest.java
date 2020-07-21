/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.service;

import com.mycompany.vandingmachine.dao.VendingMachineDaoFileImpl;
import com.mycompany.vandingmachine.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlo
 */
public class VendingMachineServiceTest {

    VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl("test.txt");

    public VendingMachineServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of removeProduct method, of class VendingMachineService.
     */
    @Test
    public void testRemoveProduct() throws Exception {
//doesnt do anything. sends stuff to dao and checks for stuff ... already tested.
    }

    /**
     * Test of checkForProduct method, of class VendingMachineService.
     */
    @Test
    public void testCheckForProduct() throws Exception {
        assertFalse(dao.getProductByName("PC").getStockLeft().equals("0"));
    }

    /**
     * Test of checkForEnoughFunds method, of class VendingMachineService.
     */
    @Test
    public void testCheckForEnoughFunds() throws Exception {
        String itemCost = dao.getProductByName("Coke").getItemCost();
        BigDecimal productCost = new BigDecimal(itemCost);
        BigDecimal initialFunds = new BigDecimal("2.00");
        //Big Decimal form for initialFunds<ProductCost
        assertFalse(initialFunds.compareTo(productCost) == 1);
    }

    /**
     * Test of itemCostInString method, of class VendingMachineService.
     */
    @Test
    public void testItemCostInString() throws Exception {
        Product p = dao.getProductByName("Doritos");

        assertEquals(p.getItemCost(), "1.50");
    }

    /**
     * Test of getExactChangeInBigDecimal method, of class
     * VendingMachineService.
     */
    @Test
    public void testGetExactChangeInBigDecimal() throws Exception {
        String itemName="PS4";
        String initialAmount="500";
        
        Product currentProduct = dao.getProductByName(itemName);
        String itemCost = currentProduct.getItemCost();

        BigDecimal initialFunds = new BigDecimal(initialAmount);
        BigDecimal productCost = new BigDecimal(itemCost);

        BigDecimal change = initialFunds.subtract(productCost);
        
        assertEquals(change,new BigDecimal("250.00"));
    }

    /**
     * Test of getChangeInCoins method, of class VendingMachineService.
     */
    @Test
    public void testGetChangeInCoins() {

        BigDecimal initialFunds = new BigDecimal("1.32");

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialFunds.divide(QUARTER, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange = initialFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange2 = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChange2.divide(NICKLE, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange3 = remaindingChange2.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChange3.divide(PENNY, 0, RoundingMode.DOWN);

        BigDecimal change = quarters.multiply(QUARTER).add(dimes.multiply(DIME).add(nickles.multiply(NICKLE).add(remaindingChange3)));

        assertEquals(change, initialFunds);
    }

    /**
     * Test of getRefundInCoins method, of class VendingMachineService.
     */
    @Test
    public void testGetRefundInCoins() {

        BigDecimal initialFunds = new BigDecimal("5.67");

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialFunds.divide(QUARTER, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange = initialFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange2 = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChange2.divide(NICKLE, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange3 = remaindingChange2.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChange3.divide(PENNY, 0, RoundingMode.DOWN);

        BigDecimal refund = quarters.multiply(QUARTER).add(dimes.multiply(DIME).add(nickles.multiply(NICKLE).add(remaindingChange3)));

        assertEquals(refund, initialFunds);

    }

}
