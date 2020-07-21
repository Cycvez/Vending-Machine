/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.dao;

import com.mycompany.vandingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author carlo
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl("test.txt");

    public VendingMachineDaoFileImplTest() {
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
     * Test of removeProduct method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        Product doritos = dao.getProductByName("Doritos");
        int startingStock = Integer.parseInt(doritos.getStockLeft());
        dao.removeProduct("Doritos");

       int newStock =  Integer.parseInt(doritos.getStockLeft());
        assertTrue(newStock == startingStock );
    }

    /**
     * Test of getInventory method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testGetInventory() throws Exception {
        List<Product> inventory = dao.getInventory();

        assertTrue(inventory.size() > 0);

    }

    /**
     * Test of getProductByName method, of class VendingMachineDaoFileImpl.
     */
    //tested on testGetInventory
    @Test
    public void testGetProductByName() throws Exception {
        Product p = dao.getProductByName("Coke");

        assertEquals(p.getName(), "Coke");

    }

}
