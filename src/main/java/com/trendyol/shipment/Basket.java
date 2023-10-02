package com.trendyol.shipment;

import java.util.*;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        if (products.size() < 3)
            return getBiggestSize();
        Map<ShipmentSize, Integer> productsSizeMap = getRepeatMap();
        ShipmentSize mostRepeatedSize = findShipmentSizeWithValueAtLeastThree(productsSizeMap);
        return mostRepeatedSize != null ? mostRepeatedSize.getNext() : getBiggestSize();
    }

    private Map<ShipmentSize, Integer> getRepeatMap() {
        Map<ShipmentSize, Integer> productsSizeMap = new EnumMap<>(ShipmentSize.class);
        for (Product product : products) {
            if (!productsSizeMap.containsKey(product.getSize())) {
                productsSizeMap.put(product.getSize(), 1);
            } else {
                productsSizeMap.put(product.getSize(), productsSizeMap.get(product.getSize()) + 1);
            }
        }
        return productsSizeMap;
    }

    private ShipmentSize findShipmentSizeWithValueAtLeastThree(Map<ShipmentSize, Integer> productsSizeMap) {

        return productsSizeMap.entrySet().stream()
                .filter(product -> product.getValue() > 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    private ShipmentSize getBiggestSize() {
        return products.stream()
                .map(Product::getSize)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
