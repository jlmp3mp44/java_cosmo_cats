package com.example.cosmocats.featuretoggle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    @Value("${feature.products.enabled:false}")
    private boolean productsEnabled;

    @Value("${feature.productDetails.enabled:false}")
    private boolean productDetailsEnabled;

    public boolean isProductsEnabled() {
        return productsEnabled;
    }

    public boolean isProductDetailsEnabled() {
        return productDetailsEnabled;
    }

    public boolean isFeatureEnabled(String featureName) {
        return switch (featureName.toLowerCase()) {
            case "products" -> productsEnabled;
            case "productdetails" -> productDetailsEnabled;
            default -> throw new IllegalArgumentException("Unknown feature: " + featureName);
        };
    }
}
