/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vandingmachine;

import com.mycompany.vandingmachine.controller.VendingMachineController;
import com.mycompany.vandingmachine.dao.VendingMachineDaoException;
import com.mycompany.vandingmachine.service.NoItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author carlog
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException, NoItemInventoryException {

        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
