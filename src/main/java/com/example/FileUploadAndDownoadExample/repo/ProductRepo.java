package com.example.FileUploadAndDownoadExample.repo;

import com.example.FileUploadAndDownoadExample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
