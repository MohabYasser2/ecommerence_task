import models.*;
import core.*;
import services.*;
import factory.ProductFactory;
import java.time.LocalDate;


public class App {
    public static void main(String[] args) throws Exception {
        // Create products using factory
        Product cheese = ProductFactory.createPerishableProduct("Cheese" , 200 , 5 , LocalDate.now().plusDays( 7 ) , 400);
        Product biscuits = ProductFactory.createPerishableProduct("Biscuits" , 150 , 3 , LocalDate.now().plusDays(30) , 700);
        Product tv = ProductFactory.createShippableProduct("TV", 1000 , 2, 15000);
        Product mobile = ProductFactory.createShippableProduct("Mobile", 500 , 5, 200);
        Product scratchCard = ProductFactory.createVirtualProduct("Scratch Card" , 10 , 100);
        //You can add more products here if needed


        // Create customer
        Customer customer = ProductFactory.createCustomer("John Doe" , 1000);
        // You Can add more customers here if needed, Like the poor customer in the example XD
        
        //Create shopping cart
        ShoppingCart cart = new ShoppingCart();
        // Also can add more than one.
        
        // Create services
        ShippingService shippingService = new StandardShippingService();
        CheckoutProcessor checkoutProcessor = new CheckoutProcessor(shippingService);
        
        // Demonstrate the system as per the example
        System.out.println("=== E-Commerce System Demo ===\n");
        
        //Add items to cart
        cart.addProduct(cheese , 2 );
        cart.addProduct(tv , 3 );
        cart.addProduct(scratchCard , 1);
        //You can add more products to the cart if needed.

        System.out.println("Items added to cart:");
        System.out.println( cart ); // Overriden toString() in ShoppingCart to display items
        
        //Process checkout
        var result = checkoutProcessor.processCheckout(customer , cart);
        
        if (result.isSuccess()) {
            System.out.println("\nCustomer balance after checkout: " + customer.getBalance());
        } else {
            System.out.println("Checkout failed: " + result.getMessage());
        }
        
        //Demonstrate the error cases.
        System.out.println("\n=== Error Case Demonstrations ===\n");
        
        // will Try to add more items than available
        ShoppingCart cart2 = new ShoppingCart();
        System.out.println("Attempting to add more biscuits than available:");
        cart2.addProduct(biscuits, 10); // Only 3 available
        
        // will Try checkout with insufficient funds
        Customer poorCustomer = ProductFactory.createCustomer(" Poor Customer ", 50);
        ShoppingCart expensiveCart = new ShoppingCart();
        expensiveCart.addProduct(tv, 1);
        
        System.out.println("\n Attempting checkout with insufficient funds: ");
        var failedResult = checkoutProcessor.processCheckout( poorCustomer , expensiveCart);
        System.out.println("Result: " + failedResult.getMessage( ) );
        
        // Try checkout with empty cart
        System.out.println("\nAttempting checkout with empty cart:");
        ShoppingCart emptyCart = new ShoppingCart();
        var emptyResult = checkoutProcessor.processCheckout(customer, emptyCart);
        System.out.println("Result: " + emptyResult.getMessage());
    }
}
