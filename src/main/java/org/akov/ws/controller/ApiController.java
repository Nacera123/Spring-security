package org.akov.ws.controller;

import org.akov.ws.model.Produit;
import org.akov.ws.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/api/produits")
    public List<Produit> home() {
        return this.produitService.getAll();
    }

}
