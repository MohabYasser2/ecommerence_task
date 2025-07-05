package utils;


public class ECommerceConfig {
    // Shipping configuration
    public static final double SHIPPING_RATE_PER_GRAM = 0.025;
    public static final double GRAM_TO_KG_CONVERSION = 1000.0;
    // public static final double KG_TO_TON_CONVERSION = 1000.0;


    //Currency formatting
    public static final String CURRENCY_FORMAT = "%.0f";
    


    //Error messages
    public static final String ERROR_EMPTY_CART = "Cart is empty";
    public static final String ERROR_INSUFFICIENT_BALANCE = "Customer's balance is insufficient";
    public static final String ERROR_PRODUCT_UNAVAILABLE = "Product %s is out of stock or expired";
    public static final String ERROR_INSUFFICIENT_STOCK = "Not enough stock for %s. Available: %d";
    public static final String ERROR_QUANTITY_EXCEEDS_STOCK = "Total quantity exceeds available stock";
    // public static final String ERROR_INVALID_PRODUCT = "Invalid product or quantity";


    // Success messages
    public static final String SUCCESS_CHECKOUT = "Checkout successful";
    public static final String SUCCESS_PRODUCT_ADDED = "Product added to cart successfully";
    
    // Receipt headers
    public static final String SHIPMENT_HEADER = "** Shipment notice **";
    public static final String RECEIPT_HEADER = "** Checkout receipt **";
    public static final String RECEIPT_SEPARATOR = "--------------------";
    
    private ECommerceConfig() {
        
    }
}
