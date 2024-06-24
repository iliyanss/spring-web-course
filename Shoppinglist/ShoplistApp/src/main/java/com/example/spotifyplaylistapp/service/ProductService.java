package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddProductDTO;
import com.example.spotifyplaylistapp.model.entity.CategoryEntity;
import com.example.spotifyplaylistapp.model.entity.CategoryEnum;
import com.example.spotifyplaylistapp.model.entity.ProductEntity;
import com.example.spotifyplaylistapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    public void addProduct(AddProductDTO addproductDTO, Long id) {
        ProductEntity productEntity = new ProductEntity();
        CategoryEntity categoryEntity =
                this.categoryService.findCategory(addproductDTO.getCategory());
        productEntity.setCategory(categoryEntity);
        productEntity.setDescription(addproductDTO.getDescription());
        productEntity.setName(addproductDTO.getName());
        productEntity.setPrice(addproductDTO.getPrice());
        productEntity.setNeededBefore(addproductDTO.getNeededBefore());
        productRepository.save(productEntity);
    }

    public List<ProductEntity> getAllFoods() {
        CategoryEntity categoryEntity = this.categoryService.findCategory(CategoryEnum.FOOD);
        return this.productRepository.findAllByCategoryEquals(categoryEntity);
    }

    public List<ProductEntity> getAllDrinks() {
        CategoryEntity categoryEntity = this.categoryService.findCategory(CategoryEnum.DRINK);
        return this.productRepository.findAllByCategoryEquals(categoryEntity);
    }

    public List<ProductEntity> getAllHouseholds() {
        CategoryEntity categoryEntity = this.categoryService.findCategory(CategoryEnum.HOUSEHOLD);
        return this.productRepository.findAllByCategoryEquals(categoryEntity);
    }

    public List<ProductEntity> getAllOthers() {
        CategoryEntity categoryEntity = this.categoryService.findCategory(CategoryEnum.OTHER);
        return this.productRepository.findAllByCategoryEquals(categoryEntity);
    }

    public double getTotalPrice(List<ProductEntity> foods,
                             List<ProductEntity> drinks,
                             List<ProductEntity> households,
                             List<ProductEntity> others) {
        double total = 0;
        for (ProductEntity food : foods) {
            total += food.getPrice();
        }
        for (ProductEntity drink : drinks) {
            total += drink.getPrice();
        }
        for (ProductEntity household : households) {
            total += household.getPrice();
        }
        for (ProductEntity other : others) {
            total += other.getPrice();
        }
        return total;
    }

    public boolean buyProduct(Long id) {
        ProductEntity productEntity = this.productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            return false;
        }
        productEntity.setCategory(null);
        this.productRepository.delete(productEntity);
        return true;
    }

    public void buyAllProducts() {
        List<ProductEntity>allProducts = this.productRepository.findAll();
        for (ProductEntity product : allProducts) {
            product.setCategory(null);
            this.productRepository.delete(product);
        }
    }
}
