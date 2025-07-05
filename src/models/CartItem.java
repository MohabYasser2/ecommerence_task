package models;

public class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() { 

        return product; 
    }
    
    public int getQuantity() { 

        return quantity; 
    }
    
    public void setQuantity(int quantity) {

        if (quantity< 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        } 
        this.quantity =quantity; 
    }
    
    public double getTotalPrice() {

        return product.getPrice() *quantity;
    }
    
    public double getTotalWeight() {

        return product.getWeight() *quantity;
    }
    
    public boolean isShippable() {

        return product.requiresShipping();
    }
    
    @Override
    public String toString() {
        return String.format("%dx %s", quantity, product.getName());
    }
}
