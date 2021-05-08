package com.ministereWeb.repository;

import com.ministereWeb.model.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitoyenRepository extends JpaRepository<Citoyen, Integer> {
    public Citoyen findByNasm(int nasm);
    public Citoyen findByNasmAndEmail(int nasm, String email);
    public Citoyen findByNomAndPrenom(String nom, String prenom);
}
