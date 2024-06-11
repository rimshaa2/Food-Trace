/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
class FoodSupplyContract extends SmartContract {
    public FoodSupplyContract(String contractId, User initiator, User participant) {
        super(contractId, initiator, participant);
    }

    @Override
    public void executeContract() {
        super.executeContract();
        System.out.println("Specific execution steps for food supply contract.");
    }
}

