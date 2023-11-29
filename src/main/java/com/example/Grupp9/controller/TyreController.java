package com.example.Grupp9.controller;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.service.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

    // URL: http://localhost:8081/api/tyres
    // Token needed

    @PostMapping("/tyres")
    public Tyre newTyre(@RequestBody Tyre tyre) {
        return tyreService.newTyre(tyre);
    }

    // URL: http://localhost:8081/api/tyres
    // Token needed
    /*
        {
	        "type": "Summer",
	        "amount": 4,
	        "price": 19.99
	    }
	*/
}
