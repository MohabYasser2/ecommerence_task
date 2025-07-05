package factory;

import models.*;
import java.time.LocalDate;


public class ProductFactory {
    

    public static PerishableProduct createPerishableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        // if (expirationDate.isBefore(LocalDate.now())) {
        //     // throw new IllegalArgumentException("Expiration date cannot be in the past");
        //     System.out.println("Expiration date cannot be in the past");
        //     return null;
        // } 
        // if (weight <= 0) {
        //     // throw new IllegalArgumentException("Weight must be greater than zero");
        //     System.out.println("Weight must be greater than zero");
        //     return null;
        // }
        // if (quantity <= 0) {
        //     // throw new IllegalArgumentException("Quantity must be greater than zero");
        //     System.out.println("Quantity must be greater than zero");
        //     return null;
        // }
        // if (name == null || name.isEmpty()) {
        //     // throw new IllegalArgumentException("Product name cannot be null or empty");
        //     System.out.println("Product name cannot be null or empty");
        //     return null;
        // }
        // if (price <= 0) {
        //     // throw new IllegalArgumentException("Price must be greater than zero");
        //     System.out.println("Price must be greater than zero");
        //     return null;
        // }

        return new PerishableProduct(name, price, quantity, expirationDate, weight);
    }
    
 
    public static DurableProduct createShippableProduct(String name, double price, int quantity, double weight) {
        //  if (expirationDate.isBefore(LocalDate.now())) {
        //     // throw new IllegalArgumentException("Expiration date cannot be in the past");
        //     System.out.println("Expiration date cannot be in the past");
        //     return null;
        // } 
        // if (weight <= 0) {
        //     // throw new IllegalArgumentException("Weight must be greater than zero");
        //     System.out.println("Weight must be greater than zero");
        //     return null;
        // }
        // if (quantity <= 0) {
        //     // throw new IllegalArgumentException("Quantity must be greater than zero");
        //     System.out.println("Quantity must be greater than zero");
        //     return null;
        // }
        // if (name == null || name.isEmpty()) {
        //     // throw new IllegalArgumentException("Product name cannot be null or empty");
        //     System.out.println("Product name cannot be null or empty");
        //     return null;
        // }
        // if (price <= 0) {
        //     // throw new IllegalArgumentException("Price must be greater than zero");
        //     System.out.println("Price must be greater than zero");
        //     return null;
        // }
        return new DurableProduct(name, price, quantity, true, weight);
    }
    

    public static DurableProduct createVirtualProduct(String name , double price , int quantity) {
        // if (expirationDate.isBefore(LocalDate.now())) {
        //     // throw new IllegalArgumentException("Expiration date cannot be in the past");
        //     System.out.println("Expiration date cannot be in the past");
        //     return null;
        // } 
        // if (weight <= 0) {
        //     // throw new IllegalArgumentException("Weight must be greater than zero");
        //     System.out.println("Weight must be greater than zero");
        //     return null;
        // }
        // if (quantity <= 0) {
        //     // throw new IllegalArgumentException("Quantity must be greater than zero");
        //     System.out.println("Quantity must be greater than zero");
        //     return null;
        // }
        // if (name == null || name.isEmpty()) {
        //     // throw new IllegalArgumentException("Product name cannot be null or empty");
        //     System.out.println("Product name cannot be null or empty");
        //     return null;
        // }
        // if (price <= 0) {
        //     // throw new IllegalArgumentException("Price must be greater than zero");
        //     System.out.println("Price must be greater than zero");
        //     return null;
        // }
        return new DurableProduct(name, price , quantity, false, 0.0);
    }
    
    public static Customer createCustomer(String name , double balance) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return new Customer(name , balance);
    }
}
