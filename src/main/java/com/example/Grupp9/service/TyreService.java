package com.example.Grupp9.service;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.TyreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TyreService {
    @Autowired
    private TyreRepository tyreRepository;

    public Tyre newTyre(Tyre tyre) {
        // Check if a Tyre with the same type exists
        Optional<Tyre> existingTyre = tyreRepository.findByType(tyre.getType());

        if (existingTyre.isPresent()) {
            // If the Tyre already exists, update the amount and save
            Tyre oldTyre = existingTyre.get();
            oldTyre.setAmount(oldTyre.getAmount() + tyre.getAmount());
            return tyreRepository.save(oldTyre);
        } else {
            // If the Tyre does not exist, save the new Tyre
            return tyreRepository.save(tyre);
        }
    }

    public List<Tyre> findAllTyres() {
        return tyreRepository.findAll();
    }

    public Tyre findTireByType(String type) {
        return tyreRepository.findByType(type).orElse(null);
    }

    public Tyre updateNewTyre(String type, Tyre tyre) {
        Optional<Tyre> tyreOptional = tyreRepository.findByType(type);

        Tyre tyre2 = new Tyre();
        if(tyreOptional.isPresent()) {
            Tyre tyre1 = tyreOptional.get();
            var addedAmount = tyre.getAmount() + tyre1.getAmount();

            tyre2.setType(tyre.getType());
            tyre2.setAmount(addedAmount);
            tyre2.setPrice(tyre.getPrice());
        }

        return tyreRepository.save(tyre2);

    }


}
