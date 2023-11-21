package com.example.Grupp9.service;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TyreService {

    private final TyreRepository tyreRepository;

    @Autowired
    public TyreService(TyreRepository tyreRepo) {
        this.tyreRepository = tyreRepo;
    }

    public List<Tyre> getAllTyres() {
        return tyreRepository.findAll();
    }
}
