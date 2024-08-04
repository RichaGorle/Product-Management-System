package com.product_task.ProductApp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product_task.ProductApp.entity.Product;


@Repository
public class ProductDao {

	@Autowired
	SessionFactory sf;

	public List<Product> showAllProduct() {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            products = session.createQuery("from Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public String addProducts(List<Product> products) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sf.openSession();
            transaction = session.beginTransaction();
            for (Product product : products) {
                session.save(product);
            }
            transaction.commit();
            return "Products added successfully";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error adding products";
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Product getProductById(long id) {
        Session session = null;
        try {
            session = sf.openSession();
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public String updateProductPrice(Long id, Double newPrice) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sf.openSession();
            Product product = session.get(Product.class, id);
            if (product != null) {
                transaction = session.beginTransaction();
                product.setPrice(newPrice);
                session.update(product);
                transaction.commit();
                return "Price updated.";
            } else {
                return "Product not found.";
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error updating price.";
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public String removeProductById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sf.openSession();
            Product product = session.get(Product.class, id);
            if (product != null) {
                transaction = session.beginTransaction();
                session.delete(product);
                transaction.commit();
                return "Product removed.";
            } else {
                return "Product not found.";
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error removing product.";
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Product> getProductsByCategory(String category) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class);
            query.setParameter("category", category);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public List<Product> getProductsByModificationDate(Product productInput) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            List<Product> allProducts = session.createQuery("FROM Product", Product.class).list();
            for (Product product : allProducts) {
                if (productInput.getDateModified().equals(product.getDateModified())) {
                    products.add(product);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public Long countProductsByCategory(String category) {
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product WHERE category = :category", Long.class);
            query.setParameter("category", category);
            Long count = query.uniqueResult();
            session.getTransaction().commit();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Product> getProductsByName(String name) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE name LIKE :name", Product.class);
            query.setParameter("name", "%" + name + "%");
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE price BETWEEN :minPrice AND :maxPrice", Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public List<Product> getProductsByPriceGreaterThan(Double price) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE price > :price", Product.class);
            query.setParameter("price", price);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public List<Product> getProductsByPriceLessThan(Double price) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE price < :price", Product.class);
            query.setParameter("price", price);
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    public List<Product> getProductsWithDescriptionKeyword(String keyword) {
        Session session = null;
        List<Product> products = new ArrayList<>();
        try {
            session = sf.openSession();
            session.beginTransaction();
            Query<Product> query = session.createQuery("FROM Product WHERE description LIKE :keyword", Product.class);
            query.setParameter("keyword", "%" + keyword + "%");
            products = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }
    public Product addProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sf.openSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
    }
}
 