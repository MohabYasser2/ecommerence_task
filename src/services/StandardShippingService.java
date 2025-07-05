package services;

import models.Product;
import utils.ECommerceConfig;
import java.util.List;


public class StandardShippingService implements ShippingService {
    
    @Override
    public void processShipping(List<Product> shippableItems) {

        if (shippableItems.isEmpty()) {

            return;
        }
        
        double totalWeight = shippableItems.stream().mapToDouble(Product::getWeight).sum();
        
        System.out.println(ECommerceConfig.SHIPMENT_HEADER);

        for (Product item : shippableItems) {

            System.out.printf("%s\t\t%.0fg%n", item.getName(), item.getWeight());

        }
        
        System.out.printf("Total package weight %.1fkg%n",totalWeight / ECommerceConfig.GRAM_TO_KG_CONVERSION);

        System.out.println();
    }
    
    @Override
    public double calculateShippingCost(double totalWeight) {
        
        return totalWeight * ECommerceConfig.SHIPPING_RATE_PER_GRAM;
    }
}
