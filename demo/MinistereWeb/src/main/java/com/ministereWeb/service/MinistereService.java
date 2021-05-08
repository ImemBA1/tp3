package com.ministereWeb.service;

import com.ministereWeb.repository.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistereService {
    @Autowired
    CitoyenRepository citoyenRepository;


    public boolean validationCitoyen1(int nasm){
        return citoyenRepository.findByNasm(nasm).isValidePermis();
    }
    public boolean validationCitoyen2(int nasm, String email){
        return citoyenRepository.findByNasmAndEmail(nasm, email).isValidePermis();
    }

}
