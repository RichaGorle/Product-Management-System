package com.product_task.ProductApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_task.ProductApp.dao.ProductDao;
import com.product_task.ProductApp.entity.Product;

@Service
public class ProductService {
	@Autowired
	ProductDao dao;
	
	public List<Product> showAllProduct() {
		return dao.showAllProduct();
		
	}
	public String addProducts(List<Product> p) {
		return dao.addProducts(p);
	}
	public Product getProductById(long id) {
		return dao.getProductById(id);
	}
	public String updateProductPrice(Long id, Double newPrice) {
		return dao.updateProductPrice(id, newPrice);
	}
	public String removeProductById(Long id) {
		return dao.removeProductById(id);
	}
	public List<Product> getProductsByCategory(String category) {
		return dao.getProductsByCategory(category);
	}
	
	 public List<Product> getProductsByModificationDate(Product p ) {
		 return dao.getProductsByModificationDate(p);
	 }
	 public Long countProductsByCategory(String category) {
		 return dao.countProductsByCategory(category);
	 }
	 public List<Product> getProductsByName(String name) {
		 return dao.getProductsByName(name);
	 }
	 public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
		 return dao.getProductsByPriceRange(minPrice, maxPrice);
	 }
	 public List<Product> getProductsByPriceGreaterThan(Double price) {
		 return dao.getProductsByPriceGreaterThan(price);
	 }
	 public List<Product> getProductsByPriceLessThan(Double price) {
		 return dao.getProductsByPriceLessThan(price);
	 }
	 public List<Product> getProductsWithDescriptionKeyword(String keyword) {
		 return dao.getProductsWithDescriptionKeyword(keyword);
	 }
	 public Product addProduct(Product product) {
		 return dao.addProduct(product);
	 }
}
