package com.ministereWeb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Citoyen implements Serializable {
    @Id
    private Integer id;
    private int nasm;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String telephone;
    private boolean validePermis;
}
