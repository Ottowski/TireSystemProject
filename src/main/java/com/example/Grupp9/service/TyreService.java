package com.example.Grupp9.service;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TyreService {

    private final TyreRepository tyreRepository;

    @Autowired
    public TyreService(TyreRepository tyreRepo) {
        this.tyreRepository = tyreRepo;
    }

    public List<Tyre> getAllTyres() {
        return tyreRepository.findAll();
    }

    public ResponseEntity<String> newTyre(Tyre tyre, @RequestBody String type) {
        try {
            tyreRepository.save(new Tyre(type));
            return ResponseEntity.ok("Tyre Created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tyre creation failed");
        }

    }
}
