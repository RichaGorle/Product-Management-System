package com.product_task.ProductApp.controller.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product_task.ProductApp.controller.ProductController;
import com.product_task.ProductApp.entity.Product;
import com.product_task.ProductApp.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController controller;

	@Mock
	private ProductService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowAllProduct() {

		Product product1 = new Product(1L, "mirror", "home decore", 1000.00, "nice flower pot",
				LocalDate.of(2024, 05, 02));
		Product product2 = new Product(2L, "iPhone 15", "electronics", 80000.00, "iOS", LocalDate.of(2024, 05, 02));
		Product product3 = new Product(3L, "laptop", "electronics", 90000.00, "Ryzen 5 processor",
				LocalDate.of(2024, 05, 03));
		Product product4 = new Product(4L, "candle holder", "home decore", 2000.00, "stylish holder",
				LocalDate.of(2024, 05, 05));
		Product product5 = new Product(5L, "remote control car", "kids play gadget", 5000.00, "car with lights",
				LocalDate.of(2024, 05, 05));
		Product product6 = new Product(6L, "guitar1", "kids play gadget", 11000.00, "brown color",
				LocalDate.of(2024, 05, 10));
		List<Product> allProducts = Arrays.asList(product1, product2, product3, product4, product5, product6);

		when(service.showAllProduct()).thenReturn(allProducts);

		List<Product> result = controller.showAllProduct();

		assertEquals(6, result.size());
		assertEquals(product1, result.get(0));
		assertEquals(product2, result.get(1));
		assertEquals(product3, result.get(2));
		assertEquals(product4, result.get(3));
		assertEquals(product5, result.get(4));
		assertEquals(product6, result.get(5));
	}

	@Test
	public void testAddProducts() {
		// Arrange
		List<Product> inputProducts = new ArrayList<>();
		inputProducts
				.add(new Product(1L, "mirror", "home decor", 1000.00, "nice flower pot", LocalDate.of(2024, 5, 2)));
		inputProducts.add(
				new Product(2L, "candle holder", "home decor", 2000.00, "stylish holder", LocalDate.of(2024, 5, 5)));
		inputProducts.add(new Product(3L, "remote control car", "kids play gadget", 5000.00, "car with lights",
				LocalDate.of(2024, 5, 5)));

		String expectedMessage = "Products added successfully";

		when(service.addProducts(inputProducts)).thenReturn(expectedMessage);
		String actualMessage = controller.addProducts(inputProducts);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testGetProductById() {

		long productId = 1L;
		Product expectedProduct = new Product(productId, "mirror", "home decore", 1000.00, "nice flower pot",
				LocalDate.of(2024, 05, 02));

		when(service.getProductById(productId)).thenReturn(expectedProduct);
		Product actualProduct = controller.getProductById(productId);
		assertEquals(expectedProduct, actualProduct);
	}

	@Test
	public void testUpdateProductPrice() {

		Long productId = 1L;
		Double newPrice = 1500.0;
		// String expectedMessage = "Price updated.";

		// when(service.updateProductPrice(productId,
		// newPrice)).thenReturn(expectedMessage);
		when(service.updateProductPrice(productId, newPrice)).thenReturn("Price updated.");
		String actualMessage = controller.updateProductPrice(productId, newPrice);
		// System.out.println(actualMessage);
		// assertEquals(expectedMessage, actualMessage);
		assertEquals("Price updated.", actualMessage);
	}

	@Test
	public void testRemoveProductById() {
		// Arrange
		Long productId = 1L;
		String expectedMessage = "Product removed.";
		when(service.removeProductById(productId)).thenReturn(expectedMessage);
		String actualMessage = controller.removeProductById(productId);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testGetProductsByCategory() {

		String category = "electronics";
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(new Product(2L, "iPhone 15", "electronics", 80000.00, "iOS", LocalDate.of(2024, 05, 02)));
		expectedProducts.add(
				new Product(3L, "laptop", "electronics", 90000.00, "Ryzen 5 processor", LocalDate.of(2024, 05, 03)));
		when(service.getProductsByCategory(category)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsByCategory(category);
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	public void testGetProductsByModificationDate() {

		LocalDate modificationDate = LocalDate.of(2024, 05, 03);
		Product testProduct = new Product(3L, "laptop", "electronics", 90000.00, "Ryzen 5 processor", modificationDate);
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(testProduct);
		when(service.getProductsByModificationDate(testProduct)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsByModificationDate(testProduct);
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	public void testCountProductsByCategory() {

		String category = "electronics";
		long expectedCount = 2;
		when(service.countProductsByCategory(category)).thenReturn(expectedCount);
		Long actualCount = controller.countProductsByCategory(category);
		assertEquals(expectedCount, actualCount);
	}

	@Test
	public void testGetProductsByName() {

		String productName = "laptop";
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(
				new Product(3L, productName, "electronics", 90000.00, "Ryzen 5 processor", LocalDate.of(2024, 05, 03)));
		when(service.getProductsByName(productName)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsByName(productName);
		assertEquals(expectedProducts, actualProducts);
	}

//	@Test
//    public void testGetProductsByName1() {
//		List<Product> expectedProducts = new ArrayList<>();
//		String productName = "laptop";
//		when(service.getProductsByName(productName)).thenReturn(expectedProducts);
//		List<Product> actualProducts = controller.getProductsByName(productName);
//        assertEquals(expectedProducts, actualProducts);
//	}

	@Test
	public void testGetProductsByPriceRange() {

		Double minPrice = 1000.0;
		Double maxPrice = 6000.0;
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts
				.add(new Product(1L, "mirror", "home decore", 1000.00, "nice flower pot", LocalDate.of(2024, 05, 02)));
		expectedProducts.add(
				new Product(4L, "candle holder", "home decore", 2000.00, "stylish holder", LocalDate.of(2024, 05, 05)));
		expectedProducts.add(new Product(5L, "remote control car", "kids play gadget", 5000.00, "car with lights",
				LocalDate.of(2024, 05, 05)));
//		expectedProducts.add(new Product(3L, "laptop", "electronics", 90000.00,
//				 "Ryzen 5 processor", LocalDate.of(2024, 05, 03)));
		when(service.getProductsByPriceRange(minPrice, maxPrice)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsByPriceRange(minPrice, maxPrice);

		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	public void testGetProductsByPriceGreaterThan() {
		// Arrange
		Double price = 70000.0;
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts
				.add(new Product(1L, "mirror", "home decore", 1000.00, "nice flower pot", LocalDate.of(2024, 5, 2)));
		expectedProducts.add(
				new Product(4L, "candle holder", "home decore", 2000.00, "stylish holder", LocalDate.of(2024, 5, 5)));
		expectedProducts.add(new Product(5L, "remote control car", "kids play gadget", 5000.00, "car with lights",
				LocalDate.of(2024, 5, 5)));
		// expectedProducts.add(new Product(2L, "iPhone 15", "electronics", 80000.00,
		// "iOS", LocalDate.of(2024, 05, 02)));
		// expectedProducts.add(new Product(3L, "laptop", "electronics", 90000.00,
		// "Ryzen 5 processor", LocalDate.of(2024, 05, 03)));

		when(service.getProductsByPriceGreaterThan(price)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsByPriceGreaterThan(price);

		assertEquals(expectedProducts, actualProducts);

	}

	@Test
	public void testGetProductsByPriceLessThan() {

		Double price = 7000.0;
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts
				.add(new Product(1L, "mirror", "home decor", 1000.00, "nice flower pot", LocalDate.of(2024, 5, 2)));
		expectedProducts.add(
				new Product(4L, "candle holder", "home decor", 2000.00, "stylish holder", LocalDate.of(2024, 5, 5)));
		expectedProducts.add(new Product(5L, "remote control car", "kids play gadget", 5000.00, "car with lights",
				LocalDate.of(2024, 5, 5)));
		expectedProducts.add(
				new Product(3L, "laptop", "electronics", 90000.00, "Ryzen 5 processor", LocalDate.of(2024, 05, 03)));
		when(service.getProductsByPriceLessThan(price)).thenReturn(expectedProducts);

		List<Product> actualProducts = controller.getProductsByPriceLessThan(price);
		for (Product product : actualProducts) {
			System.out.println("act " + product);
		}

		for (Product product : expectedProducts) {
			System.out.println("exp" + product);
		}

		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	public void testGetProductsWithDescriptionKeyword() {

		String keyword = "stylish";
		List<Product> expectedProducts = new ArrayList<>();
		expectedProducts.add(
				new Product(4L, "candle holder", "home decore", 2000.00, "stylish holder", LocalDate.of(2024, 5, 5)));
		// expectedProducts.add(new Product(6L, "vase", "home decor", 1500.00, "stylish
		// vase", LocalDate.of(2024, 5, 10)));

		when(service.getProductsWithDescriptionKeyword(keyword)).thenReturn(expectedProducts);
		List<Product> actualProducts = controller.getProductsWithDescriptionKeyword(keyword);
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
	public void testAddProduct() {

		Product inputProduct1 = new Product(1L, "mirror", "home decore", 1000.00, "nice flower pot",
				LocalDate.of(2024, 5, 2));
		Product expectedProduct1 = new Product(1L, "mirror", "home decore", 1000.00, "nice flower pot",
				LocalDate.of(2024, 5, 2));

		when(service.addProduct(inputProduct1)).thenReturn(expectedProduct1);
		Product actualProduct1 = controller.addProduct(inputProduct1);
		assertEquals(expectedProduct1, actualProduct1);
	}
}