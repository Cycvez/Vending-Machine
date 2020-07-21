/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.dao;

import com.mycompany.vandingmachine.dto.Product;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface VendingMachineDao {

    void removeProduct(String name) throws VendingMachineDaoException;

    Product addProduct(String name, Product item) throws VendingMachineDaoException;

    List<Product> getInventory() throws VendingMachineDaoException;

    

    Product getProductByName(String name) throws VendingMachineDaoException;

}
