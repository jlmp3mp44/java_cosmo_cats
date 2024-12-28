package com.example.cosmocats.featuretoggle.aspect;

import com.example.cosmocats.featuretoggle.exception.FeatureNotAvailableException;
import com.example.cosmocats.featuretoggle.service.FeatureToggleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Autowired
    public FeatureToggleAspect(FeatureToggleService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    @Before("@annotation(com.example.cosmocats.featuretoggle.annotation.FeatureToggle)")
    public void checkFeatureToggle(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        // Check for basic product operations
        if (methodName.startsWith("get") || methodName.equals("createProduct") || 
            methodName.equals("deleteProduct") || methodName.equals("updateProduct")) {
            if (!featureToggleService.isProductsEnabled()) {
                throw new FeatureNotAvailableException("The Products feature is currently disabled");
            }
        }
        
        // Check for detailed product operations
        if (methodName.equals("getProductDetails") || methodName.equals("updateStock") || 
            methodName.equals("getProductsBelowPrice")) {
            if (!featureToggleService.isProductDetailsEnabled()) {
                throw new FeatureNotAvailableException("The Product Details feature is currently disabled");
            }
        }
    }
}
