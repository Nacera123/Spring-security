package com.abdel.stock.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "produit")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "minidescription", columnDefinition = "TEXT")
    private String minidescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "prix")
    private float prix;


    @Column(name = "qte")
    private int qte;

    @Column(name = "photo_presentation")
    private String photoPresentation;


    @OneToMany
    private List<Photo> photos;



}
