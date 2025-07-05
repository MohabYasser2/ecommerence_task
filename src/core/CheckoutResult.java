package core;

/**
 * Result of a checkout operation
 */
public class CheckoutResult {
    private boolean success;
    private String message;
    private double subtotal;
    private double shippingCost;
    private double totalAmount;
    
    public CheckoutResult(boolean success, String message, double subtotal, double shippingCost, double totalAmount) {

        this.success = success;
        this.message = message;
        this.subtotal = subtotal;
        this.shippingCost = shippingCost;
        this.totalAmount = totalAmount;

    }
    
    // Getters
    public boolean isSuccess() { 
        return success; }
    public String getMessage() {
         return message; }
    public double getSubtotal() {
         return subtotal; }
    public double getShippingCost() { 
        
        return shippingCost; }
    public double getTotalAmount() {
        
        return totalAmount; }
}
