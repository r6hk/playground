package dev.rennen;

public class ProductFactory {
    public Product createProduct(String name, int price) {
        return new Product();
    }
}
