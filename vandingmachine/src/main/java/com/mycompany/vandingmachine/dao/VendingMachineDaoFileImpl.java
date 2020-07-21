/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.dao;

import com.mycompany.vandingmachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author carlo
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private String FILE;
    final String DELIMITER = "::";

    private Map<String, Product> inventory = new HashMap<>();

    public VendingMachineDaoFileImpl(String fileName) {
        FILE = fileName;
    }

    public VendingMachineDaoFileImpl() {
        FILE = "inventory.txt";
    }

    private void loadFile() throws VendingMachineDaoException {
        Scanner sc;

        try {

            sc = new Scanner(new BufferedReader(new FileReader(FILE)));

        } catch (FileNotFoundException ex) {
            throw new VendingMachineDaoException("Could not File Data.", ex);
        }

        String currentLine;
        Product currentProduct;

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentProduct = unmarshallInventory(currentLine);
            inventory.put(currentProduct.getName(), currentProduct);
        }
        sc.close();
    }

    private void writeFile() throws VendingMachineDaoException {
        PrintWriter pw;

        try {
            pw = new PrintWriter(new FileWriter(FILE));
        } catch (IOException ex) {
            throw new VendingMachineDaoException("Could not Save Inventory Data.", ex);
        }

        String inventoryAsText;
        List<Product> inventoryList = this.getInventory();

        for (Product currentProduct : inventoryList) {
            inventoryAsText = marshallProduct(currentProduct);
            pw.println(inventoryAsText);
            pw.flush();
        }
        pw.close();
    }

    private String marshallProduct(Product aItem) {
        String inventoryAsText = aItem.getName() + DELIMITER;
        inventoryAsText += aItem.getItemCost() + DELIMITER;
        inventoryAsText += aItem.getStockLeft();
        return inventoryAsText;
    }

    private Product unmarshallInventory(String inventoryAsText) {
        String[] productInfoPart = inventoryAsText.split(DELIMITER);
        String name = productInfoPart[0];

        Product productFromFile = new Product(name);

        productFromFile.setItemCost(productInfoPart[1]);
        productFromFile.setStockLeft(productInfoPart[2]);

        return productFromFile;
    }

    @Override
    public void removeProduct(String itemName) throws VendingMachineDaoException {
        getInventory();

        Product currentProduct = inventory.get(itemName);
        int currentStock = Integer.parseInt(currentProduct.getStockLeft());
        int newStock = currentStock - 1;
        currentProduct.setStockLeft(Integer.toString(newStock));

        writeFile();
    }

// you only need to add with the admin function
    @Override
    public Product addProduct(String name, Product item) throws VendingMachineDaoException {
        loadFile();
        Product newProduct = inventory.put(name, item);
        writeFile();
        return newProduct;
    }

    @Override
    public List<Product> getInventory() throws VendingMachineDaoException {
        loadFile();
        return new ArrayList<>(inventory.values());

    }

    @Override
    public Product getProductByName(String itemName) throws VendingMachineDaoException {
        getInventory();
        Product p = inventory.get(itemName);
        if (p.getName().equals(itemName)) {
            return p;
        }

        return null;
    }

//    public String getChangeInQuarters(BigDecimal exactChange) {
//        BigDecimal QUARTER = new BigDecimal(".25");
//        BigDecimal quarters = exactChange.divide(QUARTER, 0, RoundingMode.DOWN);
//        String quartersInString = quarters.toString();
//        return quartersInString;
//    }
//    public BigDecimal getChangeInDimes(BigDecimal remaindingChange) {
//
//        BigDecimal DIME = new BigDecimal(".10");
//        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
//        return dimes;
//    }
//
//    public BigDecimal getChangeInNickles(BigDecimal remaindingChange) {
//        BigDecimal NICKLE = new BigDecimal(".05");
//        BigDecimal nickles = remaindingChange.divide(NICKLE, 0, RoundingMode.DOWN);
//        return nickles;
//    }
//
//    public BigDecimal getChangeInPennies(BigDecimal remaindingChange) {
//        BigDecimal PENNY = new BigDecimal(".01");
//        BigDecimal pennies = remaindingChange.divide(PENNY, 0, RoundingMode.DOWN);
//        return pennies;
//    }
}