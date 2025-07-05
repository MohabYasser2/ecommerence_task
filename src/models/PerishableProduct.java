package models;

import java.time.LocalDate;


public class PerishableProduct extends Product {

    private LocalDate expirationDate;
    private double weight;
    
    public PerishableProduct(String name , double price , int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, true); // Perishable products require shipping
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    
    @Override
    public boolean isAvailable() {

        return isInStock() && !isExpired();
        
    }
    
    @Override
    public double getWeight() {
        
        return weight;
    }
    
    public boolean isExpired() {

        return LocalDate.now().isAfter(expirationDate);
    }
    
    public LocalDate getExpirationDate() {

        return expirationDate;
    }
    
    @Override
    public String toString() {

        return String.format("%s (Expires: %s) - Price: %.2f, Weight: %.1fg, Quantity: %d", name , expirationDate , price , weight , quantity);
    }
}
