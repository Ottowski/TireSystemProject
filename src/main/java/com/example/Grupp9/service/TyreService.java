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
    @Autowired
    private TyreRepository tyreRepository;

    public Tyre newTyre(Tyre tyre) {
        return tyreRepository.save(tyre);
    }
    public List<Tyre> findAllTyres(){
        return tyreRepository.findAll();
    }
    public Tyre findTireById(Long id) {
        return tyreRepository.findById(id).orElse(null);
    }
}
