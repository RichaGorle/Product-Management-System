package com.product_task.ProductApp.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity


public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Category is mandatory")
	private String category;

	@NotNull(message = "Price is mandatory")
	private Double price;

	private String description;

	private LocalDate  dateModified;

//	@PrePersist
//	@PreUpdate
//	private void onPersistOrUpdate() {
//		this.dateModified = new Date();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateModified() {
		return dateModified;
	}

	public void setDateModified(LocalDate dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", description="
				+ description + ", dateModified=" + dateModified + "]";
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "Category is mandatory") String category,
			@NotNull(message = "Price is mandatory") Double price, String description, LocalDate dateModified) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.dateModified = dateModified;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description) &&
                Objects.equals(dateModified, product.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price, description, dateModified);
    }
	
}
