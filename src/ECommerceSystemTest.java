import models.*;
import core.*;
import services.*;
import factory.ProductFactory;
import java.time.LocalDate;

/**
 * This is my test file for the ecommerce system
 * I tried to test all the features that I implemented
 * hopefully it works fine
 */
public class ECommerceSystemTest {
    
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE E-COMMERCE SYSTEM TEST ===\n");
        
        // lets test all the different parts of my system
        // first we test product creation
        testProductCreation();
        
        // then shopping cart stuff
        testShoppingCartOperations();
        
        // checkout process is important
        testCheckoutProcess();
        
        // need to test error cases too
        testErrorHandling();
        
        // finally test shipping 
        testShippingSystem();
        
        System.out.println("=== ALL TESTS COMPLETED ===");
    }
    
    private static void testProductCreation() {
        System.out.println("1. TESTING PRODUCT CREATION");
        System.out.println("----------------------------");
        
        // I need to create different types of products to test the factory
        // lets start with some food items that expire
        Product cheese = ProductFactory.createPerishableProduct("Cheese", 200, 5, 
                                                               LocalDate.now().plusDays(7), 400);
        
        // this milk should be expired already
        Product expiredMilk = ProductFactory.createPerishableProduct("Milk", 50, 3, 
                                                                    LocalDate.now().minusDays(1), 300);
        
        // now some electronic products
        Product tv = ProductFactory.createShippableProduct("TV", 1000, 2, 15000);
        Product mobile = ProductFactory.createShippableProduct("Mobile", 500, 5, 200);
        
        // and a virtual product that doesnt need shipping
        Product giftCard = ProductFactory.createVirtualProduct("Gift Card", 25, 50);
        
        System.out.println("Created products:");
        System.out.println("- " + cheese);
        System.out.println("- " + expiredMilk);
        System.out.println("- " + tv);
        System.out.println("- " + mobile);
        System.out.println("- " + giftCard);
        
        // lets check if the availability logic works correctly
        System.out.println("\nProduct availability check:");
        System.out.println("- Cheese available: " + cheese.isAvailable());
        System.out.println("- Expired milk available: " + expiredMilk.isAvailable());
        System.out.println("- TV available: " + tv.isAvailable());
        System.out.println();
    }
    
    private static void testShoppingCartOperations() {
        System.out.println("2. TESTING SHOPPING CART OPERATIONS");
        System.out.println("-----------------------------------");
        
        // I want to test adding products to cart
        // lets create some products first
        Product cheese = ProductFactory.createPerishableProduct("Cheese", 200, 5, 
                                                               LocalDate.now().plusDays(7), 400);
        Product tv = ProductFactory.createShippableProduct("TV", 1000, 2, 15000);
        Product scratchCard = ProductFactory.createVirtualProduct("Scratch Card", 10, 100);
        
        // create new shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // Test adding products - this should work fine
        System.out.println("Adding products to cart:");
        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 1);
        cart.addProduct(scratchCard, 3);
        
        
        // print cart contents to see what we have
        System.out.println(cart);
        System.out.println("Cart subtotal: " + cart.calculateSubtotal());
        System.out.println("Total shipping weight: " + cart.calculateTotalWeight() + "g");
        System.out.println();
    }
    
    private static void testCheckoutProcess() {
        System.out.println("3. TESTING CHECKOUT PROCESS");
        System.out.println("---------------------------");
        
        // Setup everything we need for checkout
        // customer with enough money
        Customer customer = ProductFactory.createCustomer("Alice Johnson", 2000);
        ShoppingCart cart = new ShoppingCart();
        ShippingService shippingService = new StandardShippingService();
        CheckoutProcessor processor = new CheckoutProcessor(shippingService);
        
        // Add some products to cart that customer can afford
        Product cheese = ProductFactory.createPerishableProduct("Cheese", 200, 5, 
                                                               LocalDate.now().plusDays(7), 400);
        Product biscuits = ProductFactory.createPerishableProduct("Biscuits", 150, 3, 
                                                                 LocalDate.now().plusDays(30), 700);
        
        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);

        
        // show customer info before checkout
        System.out.println("Customer before checkout: " + customer);
        System.out.println("Cart contents: " + cart.getItems().size() + " items");
        
        // now lets process the checkout and see what happens
        CheckoutResult result = processor.processCheckout(customer, cart);
        
        System.out.println("Checkout result: " + result.getMessage());
        System.out.println("Customer after checkout: " + customer);
        System.out.println();
    }
    
    private static void testErrorHandling() {
        System.out.println("4. TESTING ERROR HANDLING");
        System.out.println("-------------------------");
        
        // I need to test different error scenarios 
        // to make sure my system handles them properly
        
        // first create some products for testing
        Product limitedStock = ProductFactory.createShippableProduct("Limited Item", 100, 1, 500);
        Product expiredProduct = ProductFactory.createPerishableProduct("Expired Food", 50, 5, 
                                                                       LocalDate.now().minusDays(2), 200);
        
        // customer with very little money
        Customer poorCustomer = ProductFactory.createCustomer("Poor Customer", 10);
        ShoppingCart cart = new ShoppingCart();
        CheckoutProcessor processor = new CheckoutProcessor(new StandardShippingService());
        
        // Test 1: try to add more items than we have in stock
        System.out.println("Test 1 - Adding more than available stock:");
        cart.addProduct(limitedStock, 5); // Should fail because we only have 1
        
        // Test 2: try to add expired product
        System.out.println("Test 2 - Adding expired product:");
        cart.addProduct(expiredProduct, 1); // Should fail because its expired
        
        // Test 3: checkout with not enough money
        System.out.println("Test 3 - Checkout with insufficient funds:");
        cart.addProduct(limitedStock, 1); // This should work
        CheckoutResult result = processor.processCheckout(poorCustomer, cart);
        System.out.println("Result: " + result.getMessage());

        
        // Test 4: checkout with empty cart
        System.out.println("Test 4 - Empty cart checkout:");
        ShoppingCart emptyCart = new ShoppingCart();
        CheckoutResult emptyResult = processor.processCheckout(poorCustomer, emptyCart);
        System.out.println("Result: " + emptyResult.getMessage());
        System.out.println();
    }
    
    private static void testShippingSystem() {
        System.out.println("5. TESTING SHIPPING SYSTEM");
        System.out.println("--------------------------");
        
        // create different products with different shipping needs
        // some need shipping, some dont
        Product tv = ProductFactory.createShippableProduct("TV", 1000, 2, 15000);
        Product mobile = ProductFactory.createShippableProduct("Mobile", 500, 5, 200);
        Product cheese = ProductFactory.createPerishableProduct("Cheese", 200, 5, 
                                                               LocalDate.now().plusDays(7), 400);
        Product digitalGame = ProductFactory.createVirtualProduct("Digital Game", 60, 10);
        
        // rich customer who can afford everything
        Customer customer = ProductFactory.createCustomer("Tech Enthusiast", 5000);
        ShoppingCart cart = new ShoppingCart();
        
        // Add mix of products - some virtual, some physical
        cart.addProduct(tv, 1);
        cart.addProduct(mobile, 2);
        cart.addProduct(cheese, 1);
        cart.addProduct(digitalGame, 1);  // this one shouldnt be shipped
        
        System.out.println("Cart with mixed products:");
        System.out.println("- Shippable items: " + cart.getShippableItems().size());
        System.out.println("- Total weight: " + cart.calculateTotalWeight() + "g");
        
        // Process checkout to see how shipping works
        CheckoutProcessor processor = new CheckoutProcessor(new StandardShippingService());
        CheckoutResult result = processor.processCheckout(customer, cart);

        
        System.out.println("Final result: " + result.getMessage());
        System.out.println();
    }
}
