package services;

import models.Product;
import java.util.List;


public interface ShippingService {
    
    void processShipping( List<Product> shippableItems);
    
    double calculateShippingCost( double totalWeight);
}
