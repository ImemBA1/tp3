package com.ministereWeb.controller;

import com.ministereWeb.repository.CitoyenRepository;
import com.ministereWeb.service.MinistereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3535")
public class MinistereController {

    @Autowired
    CitoyenRepository citoyenRepository;
    @Autowired
    MinistereService ministereService;

    @GetMapping("/ministere/{nasm}")
    public boolean validerUser(@PathVariable int nasm){
        return ministereService.validationCitoyen1(nasm);
    }

    @GetMapping("/ministere")
    public boolean validerUser2(@RequestParam int nasm, @RequestParam String email){
        return ministereService.validationCitoyen2(nasm, email);
    }
}
