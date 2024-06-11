/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private List<User> users = new ArrayList<>();
    private User currentUser;
    private Cart cart = new Cart();

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                currentUser = u;
                return currentUser;
            }
        }
        return null; // Handle login failure appropriately
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        users.add(user);
        currentUser = user;
        return currentUser;
    }

    @PostMapping("/addProduct")
    public Cart addProduct(@RequestBody Inventory product) {
        cart.addItem(product);
        return cart;
    }

    @GetMapping("/cart")
    public Cart viewCart() {
        return cart;
    }

    // Add other endpoints as necessary
}

