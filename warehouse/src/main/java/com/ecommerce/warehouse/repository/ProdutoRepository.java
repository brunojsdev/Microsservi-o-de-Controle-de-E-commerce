package com.ecommerce.warehouse.repository;

import com.ecommerce.warehouse.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Usamos String porque definimos o nome do produto como ID
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
}