/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
import java.util.ArrayList;

class Cart {
    private ArrayList<Inventory> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Inventory item) {
        items.add(item);
        System.out.println("Added " + item.getProductName() + " to cart.");
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart items:");
            for (Inventory item : items) {
                item.displayProductDetails();
            }
        }
    }
}

