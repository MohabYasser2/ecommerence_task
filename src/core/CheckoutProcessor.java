package core;

import models.*;
import services.ShippingService;
import java.util.List;

public class CheckoutProcessor {
    private ShippingService shippingService;
    
    public CheckoutProcessor(ShippingService shippingService) {
        this.shippingService = shippingService;
    }
    
    public CheckoutResult processCheckout(Customer customer, ShoppingCart cart) {
        // Validation checks
        if (cart.isEmpty()) {

            return new CheckoutResult(false, "Cart is empty", 0, 0, 0);
        }
        
        // Check product availability and stock
        for (CartItem item : cart.getItems()) {
            if (!item.getProduct().isAvailable()) {


                return new CheckoutResult(false, 
                    "Product " + item.getProduct().getName() + " is out of stock or expired", 
                    0 , 0 , 0  );


            }
        }
        
        // Calculate costs
        double subtotal = cart.calculateSubtotal() ;
        double shippingCost = 0 ;
        
        List<Product> shippableItems = cart.getShippableItems() ;
        if ( !shippableItems.isEmpty() ) {

            double totalWeight = cart.calculateTotalWeight();
            shippingCost = shippingService.calculateShippingCost( totalWeight );
        }
        
        double totalAmount = subtotal + shippingCost;
        
        // Check customer balance
        if (!customer.hasEnoughBalance(totalAmount)) {

            return new CheckoutResult(false , "Customer's balance is insufficient" , subtotal, shippingCost, totalAmount);
        }
        
        // Process the order
        processOrder(customer , cart , subtotal, shippingCost, totalAmount );
        

        return new CheckoutResult(true, "Checkout successful", subtotal, shippingCost, totalAmount);
    }
    
    
    private void processOrder(Customer customer, ShoppingCart cart, 
                            double subtotal, double shippingCost, double totalAmount) {
        // Deduct product quantities
        for (CartItem item : cart.getItems()) {
            
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        
        // Charge customer
        customer.deductBalance( totalAmount);
        
        // Handle shipping
        List<Product> shippableItems = cart.getShippableItems();
        if (!shippableItems.isEmpty()) {

            shippingService.processShipping(shippableItems );
        }
        
        // Print receipt
        printReceipt(cart , subtotal , shippingCost, totalAmount);


    }
    
    
    private void printReceipt(ShoppingCart cart, double subtotal, double shippingCost, double totalAmount) {
        
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {

            System.out.printf("%dx %s\t\t%.0f%n",item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }


        System.out.println("--------------------");
        System.out.printf("Subtotal\t\t%.0f%n", subtotal);
        System.out.printf("Shipping\t\t%.0f%n", shippingCost);
        System.out.printf("Amount\t\t\t%.0f%n", totalAmount);


    }
}
