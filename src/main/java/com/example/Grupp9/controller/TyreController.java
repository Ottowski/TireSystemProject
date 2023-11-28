package com.example.Grupp9.controller;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.service.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return tyreService.findAllTyres();
    }

    @PostMapping("/tyres")
    public Tyre newTyre(@RequestBody Tyre tyre) {
        return tyreService.newTyre(tyre);
    }





//    public ResponseEntity<String> newTyre(Tyre tyre, @RequestBody String type) {
//        try {
//            tyreRepository.save(new Tyre(type));
//            return ResponseEntity.ok("Tyre Created");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Tyre creation failed");
//        }
//
//    }
}
