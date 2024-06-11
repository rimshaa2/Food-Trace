/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
import javax.swing.*;
import java.awt.*;

 class SmartContract {
        private String contractName;
        private String termsAndConditions;

        public SmartContract(String contractName, String termsAndConditions) {
            this.contractName = contractName;
            this.termsAndConditions = termsAndConditions;
        }

        public String getContractName() {
            return contractName;
        }

        public String getTermsAndConditions() {
            return termsAndConditions;
        }
    }



