package com.abdel.stock.service;


import com.abdel.stock.model.Produit;
import com.abdel.stock.repository.ProduitRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Data
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;


    public  List<Produit> getAll(){

        return produitRepository.findAll();
    }

    public Produit getById( Integer id){
        return  produitRepository.findById(id).orElse(null);
    }

    public Produit save(Produit p){
        return  produitRepository.save(p);
    }

    public void  delete(Integer id){
    Produit p= produitRepository.findById(id).orElse(null);
    produitRepository.delete(p);

    }



}
