package models;


public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected boolean requiresShipping;
    
    public Product(String name, double price, int quantity, boolean requiresShipping) {

        this.name = name;
        this.price =price;
        this.quantity =quantity;
        this.requiresShipping =requiresShipping;
    }
    
    // Getters
    public String getName() { 

        return name; }
    public double getPrice() { 

        return price; }
    public int getQuantity() { return quantity; }
    public boolean requiresShipping() {

        return requiresShipping; }
    
    // Setters
    public void setQuantity(int quantity) { 
        
        this.quantity = quantity; }
    
    // Abstract methods
    public abstract boolean isAvailable();
    public abstract double getWeight();
    
    // Common methods
    public boolean isInStock() {

        return quantity > 0;
    }
    
    public void decreaseQuantity(int amount) {

        if (amount <= quantity) {
            quantity -= amount;
        }
    }
    
    @Override
    public String toString() {

        return String.format("%s - Price: %.2f, Quantity: %d", name, price, quantity);
    }
}
