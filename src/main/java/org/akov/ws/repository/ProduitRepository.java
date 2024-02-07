package com.abdel.stock.repository;


import com.abdel.stock.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository  extends JpaRepository<Produit ,Integer> {

}
