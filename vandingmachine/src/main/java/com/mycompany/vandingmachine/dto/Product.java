/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author carlo
 */
public class Product {

    private String name;
    private String itemCost;
    private String stockLeft;

    
    
    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getStockLeft() {
        return stockLeft;
    }

    public void setStockLeft(String stockLeft) {
        this.stockLeft = stockLeft;
    }

}
