package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT products.name " +
            "FROM products " +
            "FULL OUTER JOIN providers " +
            "ON products.id_providers = providers.id " +
            "WHERE providers.name LIKE :nameLike " +
            "AND products.amount BETWEEN :minAmount AND :maxAmount")
    List<ProductMinProjection> search1(String nameLike, Integer minAmount, Integer maxAmount);

    @Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " +
            "FROM Product obj " +
            "WHERE obj.provider.name LIKE :nameLike " +
            "AND obj.amount BETWEEN :minAmount AND :maxAmount")
    List<ProductMinDTO> search2(String nameLike, Integer minAmount, Integer maxAmount);
}