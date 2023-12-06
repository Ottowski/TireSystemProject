package com.example.Grupp9.service;

import com.example.Grupp9.exception.NotFoundException;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TyreService {

    private final TyreRepository tyreRepository;

    public TyreService(TyreRepository tyreRepository) {
        this.tyreRepository = tyreRepository;
    }

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

            tyre2.setType(tyre1.getType());
            tyre2.setAmount(addedAmount);
            tyre2.setPrice(tyre1.getPrice());
        }

        return tyreRepository.save(tyre2);

    }


    public void deleteTyre(String type) {
        Tyre tyre = tyreRepository.findByType(type)
                .orElseThrow(() -> new NotFoundException("Not found this item " + type));
         tyreRepository.delete(tyre);
    }
}
