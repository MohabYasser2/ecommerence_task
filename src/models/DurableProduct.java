package models;


public class DurableProduct extends Product {
    private double weight;
    
    public DurableProduct(String name , double price , int quantity , boolean requiresShipping , double weight) {

        super(name , price , quantity, requiresShipping);
        this.weight = weight;
    }
    
    @Override
    public boolean isAvailable() {

        return isInStock();
    }
    
    @Override
    public double getWeight() {

        return weight;
    }
    
    @Override
    public String toString() {

        String shippingInfo = requiresShipping ? String.format("Weight: %.1fg, ", weight) : "";
        return String.format("%s - Price: %.2f, %sQuantity: %d", 
                           name, price, shippingInfo, quantity);
    }
}
