package com.product_task.ProductApp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_task.ProductApp.entity.Product;
import com.product_task.ProductApp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("showAllProduct")
	public List<Product> showAllProduct() {
		return service.showAllProduct();
	}

	@PostMapping("addProducts")
	public String addProducts(@RequestBody List<Product> p) {
		return service.addProducts(p);
	}

	@GetMapping("getProductById/{id}")
	public Product getProductById(@PathVariable long id) {
		return service.getProductById(id);
	}

	@PutMapping("updateProductPrice/{id}/{price}")
	public String updateProductPrice(@PathVariable Long id, @PathVariable Double price) {
		return service.updateProductPrice(id, price);
	}

	@DeleteMapping("removeProductById/{id}")
	public String removeProductById(@PathVariable Long id) {
		return service.removeProductById(id);
	}

	@GetMapping("getProductsByCategory/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category) {
		return service.getProductsByCategory(category);
	}

	@GetMapping("getProductsByModificationDate")
	public List<Product> getProductsByModificationDate(@RequestBody Product p) {
		return service.getProductsByModificationDate(p);
	}

	@GetMapping("countProductsByCategory/{category}")
	public Long countProductsByCategory(@PathVariable String category) {
		return service.countProductsByCategory(category);
	}
	@GetMapping("getProductsByName/{name}")
	public List<Product> getProductsByName(@PathVariable String name) {
		return service.getProductsByName(name);
	 }
	@GetMapping("getProductsByPriceRange/{minPrice}/{maxPrice}")
	 public List<Product> getProductsByPriceRange(@PathVariable Double minPrice,@PathVariable Double maxPrice) {
		 return service.getProductsByPriceRange(minPrice, maxPrice);
	 }
	@GetMapping("getProductsByPriceGreaterThan/{price}")
	public List<Product> getProductsByPriceGreaterThan( @PathVariable Double price) {
		 return service.getProductsByPriceGreaterThan(price);
	 }
	 @GetMapping("getProductsByPriceLessThan/{price}")
	public List<Product> getProductsByPriceLessThan(@PathVariable Double price) {
		 return service.getProductsByPriceLessThan(price);
	 }
	 @GetMapping("getProductsWithDescriptionKeyword/{keyword}")
	 public List<Product> getProductsWithDescriptionKeyword(@PathVariable String keyword) {
		 return service.getProductsWithDescriptionKeyword(keyword);
	 }
	 @PostMapping("add")
	 public Product addProduct(Product product) {
		 Product out= service.addProduct(product);
		return service.addProduct(product);
	 }
}
