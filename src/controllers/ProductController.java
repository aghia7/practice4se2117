package controllers;

import models.Product;
import repositories.products.IProductRepository;

import java.util.List;

public class ProductController {
    private final IProductRepository productRepo;

    public ProductController(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public String create(String name, String category, double price) {
        Product product = Product.builder(name)
                .withCategory(category).withPrice(price).build();

        boolean created = productRepo.create(product);

        if (created)
            return "A new product " + name + " was created successfully!";

        return "Product cannot be created!";
    }

    public String getById(int id) {
        Product product = productRepo.get(id);

        if (product == null)
            return "Product with id = " + id + " does not exist!";

        return product.toString();
    }

    public String removeById(int id) {
        Product product = productRepo.get(id);

        if (product == null)
            return "Product with id = " + id + " does not exist!";

        boolean removed = productRepo.delete(id);

        if (removed)
            return "Product " + product.getName() + " was removed successfully!";

        return "Product cannot be removed!";
    }

    public String getAll() {
        List<Product> products = productRepo.getAll();

        if (products.isEmpty())
            return "Product`s list is empty";

        StringBuilder response = new StringBuilder();
        for (Product product : products)
            response.append(product).append("\n");

        return response.toString();
    }

    public String getAllByCategory(String category) {
        List<Product> products = productRepo.getAllByCategory(category);

        if (products.isEmpty())
            return "Product`s list is empty";

        StringBuilder response = new StringBuilder();
        for (Product product : products)
            response.append(product).append("\n");

        return response.toString();
    }
}
