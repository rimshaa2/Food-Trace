/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
class Producer extends User {
    public Producer(String name, String email, String password) {
        super(name, email, password);
    }

    public void displayRole() {
        System.out.println(name + " is a Producer.");
    }

    // Additional producer-specific methods
}