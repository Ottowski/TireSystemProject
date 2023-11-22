package com.example.Grupp9.controller;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.service.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TyreController {

    private final TyreService tyreService;

    @Autowired
    public TyreController(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    @GetMapping("/tyres")
    public List<Tyre> getAllTyres() {
        return tyreService.getAllTyres();
    }

    @PostMapping("/tyres")
    public ResponseEntity<String> newTyre(Tyre tyre, String type) {
        return tyreService.newTyre(tyre, type);
    }
}
