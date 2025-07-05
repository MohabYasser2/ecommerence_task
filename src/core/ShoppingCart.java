package core;

import models.*;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private List<CartItem> items;
    
    public ShoppingCart() {

        this.items = new ArrayList<>();
    }
    
   
    public boolean addProduct(Product product, int quantity) {

        if ( product == null || quantity <= 0 ) {

            return false;
        }
        
        if (!product.isAvailable()) {

            System.out.println("Error: Product " + product.getName() + " is not available");
            return false;

        }
        
        if (quantity > product.getQuantity()) {

            System.out.println("Error: Not enough stock for " + product.getName() + ". Available: " + product.getQuantity());
            return false;

        }
        
        // Check if product already exists in cart
        for (CartItem item : items) {

            if (item.getProduct().equals(product )) {

                int newQuantity = item.getQuantity() +quantity;
                if (newQuantity > product.getQuantity( ) ) {

                    System.out.println("Error: Total quantity exceeds available stock");
                    return false;
                }
                item.setQuantity(newQuantity);
                return true;

            }
        }
        
        // Add new item to cart
        items.add(new CartItem(product, quantity));
        return true;
    }
    
  
    public List<CartItem> getItems() { 

        return new ArrayList<>(items);
    }
    
   
    public boolean isEmpty() {

        return items.isEmpty();
    }
    
   
    public double calculateSubtotal() {

        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    
  
    public List<Product> getShippableItems() {

        List<Product> shippableItems = new ArrayList<>();
        for (CartItem item : items) {

            if (item.isShippable()) {


                // Add each product instance based on quantity
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(item.getProduct());
                }

            }

        }
        return shippableItems;
    }

    
    public double calculateTotalWeight() {
        return items.stream().filter(CartItem::isShippable).mapToDouble(CartItem::getTotalWeight).sum();
    }
 
    public void clear() {
        
        items.clear();
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Shopping Cart:\n");
        for (CartItem item : items) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
